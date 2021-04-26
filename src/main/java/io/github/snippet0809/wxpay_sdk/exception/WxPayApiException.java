package io.github.snippet0809.wxpay_sdk.exception;

public class WxPayApiException extends Exception {

    private String wxPayApiHttpRequestUrl;
    private int wxPayApiHttpResponseCode;
    private String wxPayApiHttpResponseEntity;

    public WxPayApiException(String wxPayApiHttpRequestUrl, int wxPayApiHttpResponseCode, String wxApiHttpResponseEntity) {
        super(wxApiHttpResponseEntity);
        this.wxPayApiHttpRequestUrl = wxPayApiHttpRequestUrl;
        this.wxPayApiHttpResponseCode = wxPayApiHttpResponseCode;
        this.wxPayApiHttpResponseEntity = wxApiHttpResponseEntity;
    }

    public String getWxPayApiHttpRequestUrl() {
        return wxPayApiHttpRequestUrl;
    }

    public WxPayApiException setWxPayApiHttpRequestUrl(String wxPayApiHttpRequestUrl) {
        this.wxPayApiHttpRequestUrl = wxPayApiHttpRequestUrl;
        return this;
    }

    public int getWxPayApiHttpResponseCode() {
        return wxPayApiHttpResponseCode;
    }

    public WxPayApiException setWxPayApiHttpResponseCode(int wxPayApiHttpResponseCode) {
        this.wxPayApiHttpResponseCode = wxPayApiHttpResponseCode;
        return this;
    }

    public String getWxPayApiHttpResponseEntity() {
        return wxPayApiHttpResponseEntity;
    }

    public WxPayApiException setWxPayApiHttpResponseEntity(String wxPayApiHttpResponseEntity) {
        this.wxPayApiHttpResponseEntity = wxPayApiHttpResponseEntity;
        return this;
    }
}
