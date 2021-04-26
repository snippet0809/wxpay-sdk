package io.github.snippet0809.wxpay_sdk.httpclient;

import io.github.snippet0809.wxpay_sdk.security.SignProducer;
import io.github.snippet0809.wxpay_sdk.security.SignVerifier;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.*;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.execchain.ClientExecChain;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class WxPayHttpClientBuilder extends HttpClientBuilder {

    private final SignProducer signProducer;
    private final SignVerifier signVerifier;

    public WxPayHttpClientBuilder(SignProducer signProducer, SignVerifier signVerifier) {
        this.signProducer = signProducer;
        this.signVerifier = signVerifier;
    }

    @Override
    protected ClientExecChain decorateProtocolExec(ClientExecChain protocolExec) {
        return (httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware) -> {
            // 一、请求前要在header上带上签名
            addAuth(httpRequestWrapper);
            // 二、发送请求，拿到响应
            CloseableHttpResponse response = protocolExec.execute(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
            // 二、响应状态码为200要验证签名
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() >= 200 && statusLine.getStatusCode() < 300 && signVerifier != null) {
                boolean signIsRight = checkSign(response);
                if (!signIsRight) {
                    throw new RuntimeException("微信支付响应的签名有误");
                }
            }
            return response;
        };
    }

    /**
     * 向微信发送请求时，携带上签名
     */
    private void addAuth(HttpRequestWrapper httpRequestWrapper) throws IOException {
        URI uri = httpRequestWrapper.getURI();
        String url = uri.getQuery() == null ? uri.getRawPath() : uri.getRawPath() + "?" + uri.getRawQuery();
        String body = "";
        if (httpRequestWrapper instanceof HttpEntityEnclosingRequest) {
            body = EntityUtils.toString(((HttpEntityEnclosingRequest) httpRequestWrapper).getEntity(), StandardCharsets.UTF_8);
        }
        String nonceStr = SignProducer.generateNonceStr(), timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String sign = signProducer.generateSign(httpRequestWrapper.getMethod(), url, timestamp, nonceStr, body);
        String auth = "mchid=\"" + signProducer.getMchId() + "\",serial_no=\"" + signProducer.getSerialNo() +
                "\",nonce_str=\"" + nonceStr + "\",timestamp=\"" + timestamp + "\",signature=\"" + sign + "\"";
        httpRequestWrapper.addHeader("Authorization", "WECHATPAY2-SHA256-RSA2048 " + auth);
    }

    /**
     * 收到微信的响应后，验证签名
     */
    private boolean checkSign(CloseableHttpResponse response) throws IOException {
        String serialNo = response.getFirstHeader("Wechatpay-Serial").getValue();
        String timestamp = response.getFirstHeader("Wechatpay-Timestamp").getValue();
        String nonce = response.getFirstHeader("Wechatpay-Nonce").getValue();
        String signInHeader = response.getFirstHeader("Wechatpay-Signature").getValue();
        makeEntityRepeatable(response);
        HttpEntity httpEntity = response.getEntity();
        String body = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
        if (signVerifier != null) {
            return signVerifier.verifySign(serialNo, timestamp, nonce, body, signInHeader);
        }
        return true;
    }

    /**
     * 将CloseableHttpResponse的body参数设为可重读
     */
    private void makeEntityRepeatable(CloseableHttpResponse response) throws IOException {
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            response.setEntity(new BufferedHttpEntity(httpEntity));
        }
    }
}
