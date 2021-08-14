package io.github.snippet0809.wxpay_sdk.httpclient;

import io.github.snippet0809.wxpay_sdk.exception.WxPayApiException;
import io.github.snippet0809.wxpay_sdk.security.SignProducer;
import io.github.snippet0809.wxpay_sdk.security.SignVerifier;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WxPayHttpClient {

    private final CloseableHttpClient wxPayHttpClientInstance;

    public WxPayHttpClient(SignProducer signProducer, SignVerifier signVerifier) {
        this.wxPayHttpClientInstance = new WxPayHttpClientBuilder(signProducer, signVerifier).build();
    }

    public String get(String url) throws IOException, WxPayApiException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "application/json");
        CloseableHttpResponse response = wxPayHttpClientInstance.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();
        String res = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() < 200 || statusLine.getStatusCode() >= 300) {
            throw new WxPayApiException(url, statusLine.getStatusCode(), res);
        }
        return res;
    }

    public String post(String url, String body) throws IOException, WxPayApiException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        httpPost.addHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity(body, StandardCharsets.UTF_8.name()));
        CloseableHttpResponse response = wxPayHttpClientInstance.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        String res = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() < 200 || statusLine.getStatusCode() >= 300) {
            throw new WxPayApiException(url, statusLine.getStatusCode(), res);
        }
        return res;
    }
}
