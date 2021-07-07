package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class OrderResult {

    @JSONField(name = "prepay_id")
    private String prepayId;

    public String getPrepayId() {
        return prepayId;
    }

    public OrderResult setPrepayId(String prepayId) {
        this.prepayId = prepayId;
        return this;
    }
}
