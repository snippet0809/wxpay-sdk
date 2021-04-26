package io.github.snippet0809.wxpay_sdk.security;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.snippet0809.wxpay_sdk.constant.WxApi;
import io.github.snippet0809.wxpay_sdk.exception.WxPayApiException;
import io.github.snippet0809.wxpay_sdk.httpclient.WxPayHttpClientBuilder;
import io.github.snippet0809.wxpay_sdk.pojo.Cert;
import io.github.snippet0809.wxpay_sdk.security.AesDecoder;
import io.github.snippet0809.wxpay_sdk.security.SignProducer;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignVerifier {

    private final Map<String, X509Certificate> x509CertificateMap;    // 微信支付平台的证书集合

    private SignProducer signProducer;
    private AesDecoder aesDecoder;

    public SignVerifier(Map<String, X509Certificate> x509CertificateMap) {
        this.x509CertificateMap = x509CertificateMap;
    }

    public SignVerifier(SignProducer signProducer, AesDecoder aesDecoder) throws WxPayApiException, IOException {
        this.signProducer = signProducer;
        this.aesDecoder = aesDecoder;
        this.x509CertificateMap = new HashMap<>();
        updateCert();
    }


    /**
     * 验证签名
     */
    public boolean verifySign(String serialNo, String timestamp, String nonceStr, String body, String signature) {
        // 一、选定指定序列号的证书，若不存在则向微信服务器发请求更新
        X509Certificate x509Certificate = x509CertificateMap.get(serialNo);
        if (x509Certificate == null) {
            try {
                updateCert();
            } catch (WxPayApiException | IOException e) {
                throw new RuntimeException("找不到指定序列号的支付平台证书且证书更新失败", e);
            }
        }
        x509Certificate = x509CertificateMap.get(serialNo);
        if (x509Certificate == null) {
            throw new RuntimeException("支付平台证书更新成功但仍找不到指定序列号的证书");
        }
        // 二、验证签名
        String str = timestamp + "\n" + nonceStr + "\n" + body + "\n";
        try {
            Signature sha256withRSA = Signature.getInstance("SHA256withRSA");
            sha256withRSA.initVerify(x509Certificate);
            sha256withRSA.update(str.getBytes(StandardCharsets.UTF_8));
            return sha256withRSA.verify(Base64.getDecoder().decode(signature));
        } catch (Exception e) {
            throw new RuntimeException("验证签名过程出现异常", e);
        }
    }

    /**
     * 更新微信支付平台证书
     */
    public void updateCert() throws WxPayApiException, IOException {
        // 一、发送请求
        CloseableHttpClient wxPayHttpClientWithoutVerifier = new WxPayHttpClientBuilder(signProducer, null).build();
        HttpGet httpGet = new HttpGet(WxApi.CERT_DOWN);
        httpGet.setHeader("Accept", "application/json");
        CloseableHttpResponse response = wxPayHttpClientWithoutVerifier.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();
        String body = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() < 200 || statusLine.getStatusCode() >= 300) {
            throw new WxPayApiException(WxApi.CERT_DOWN, statusLine.getStatusCode(), body);
        }
        // 二、解析响应
        JSONObject jsonObject = JSONObject.parseObject(body);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        String jsonString = JSONArray.toJSONString(jsonArray);
        List<Cert> certList = JSONArray.parseArray(jsonString, Cert.class);
        // 三、解密并将字符串反序列化成证书对象
        for (Cert cert : certList) {
            Cert.EncryptCertificate encryptCertificate = cert.getEncryptCertificate();
            try {
                String certStr = aesDecoder.decryptToString(encryptCertificate.getAssociatedData(), encryptCertificate.getNonce(), encryptCertificate.getCiphertext());
                CertificateFactory cf = CertificateFactory.getInstance("X509");
                X509Certificate x509Cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(certStr.getBytes(StandardCharsets.UTF_8)));
                x509Cert.checkValidity();
                x509CertificateMap.put(cert.getSerialNo(), x509Cert);
            } catch (Exception e) {
                throw new RuntimeException("自动拉取微信支付平台证书成功，但对证书解密失败", e);
            }
        }
    }
}