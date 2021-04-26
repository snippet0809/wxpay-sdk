package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class WakeJsapiPayParam {

    private String appId;
    private String timeStamp;
    private String nonceStr;
    @JSONField(name = "package")
    private String _package;
    private String signType;
    private String paySign;

    public String getAppId() {
        return appId;
    }

    public WakeJsapiPayParam setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public WakeJsapiPayParam setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public WakeJsapiPayParam setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
        return this;
    }

    public String get_package() {
        return _package;
    }

    public WakeJsapiPayParam set_package(String _package) {
        this._package = _package;
        return this;
    }

    public String getSignType() {
        return signType;
    }

    public WakeJsapiPayParam setSignType(String signType) {
        this.signType = signType;
        return this;
    }

    public String getPaySign() {
        return paySign;
    }

    public WakeJsapiPayParam setPaySign(String paySign) {
        this.paySign = paySign;
        return this;
    }
}
