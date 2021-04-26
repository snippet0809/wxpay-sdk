package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class WakeAppPayParam {

    private String appid;
    @JSONField(name = "partnerid")
    private String partnerId;
    @JSONField(name = "prepayid")
    private String prepayId;
    @JSONField(name = "package")
    private String _package;
    @JSONField(name = "noncestr")
    private String nonceStr;
    private String timestamp;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public WakeAppPayParam setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public WakeAppPayParam setPartnerId(String partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public WakeAppPayParam setPrepayId(String prepayId) {
        this.prepayId = prepayId;
        return this;
    }

    public String get_package() {
        return _package;
    }

    public WakeAppPayParam set_package(String _package) {
        this._package = _package;
        return this;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public WakeAppPayParam setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public WakeAppPayParam setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public WakeAppPayParam setSign(String sign) {
        this.sign = sign;
        return this;
    }
}
