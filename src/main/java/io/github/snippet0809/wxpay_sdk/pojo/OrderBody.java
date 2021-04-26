package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class OrderBody {

    private String appid;
    @JSONField(name = "mchid")
    private String mchId;
    private String description;
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "time_expire")
    private String timeExpire;
    private String attach;
    @JSONField(name = "notify_url")
    private String notifyUrl;
    @JSONField(name = "good_tag")
    private String goodsTag;
    private Amount amount;
    private Payer payer;

    public String getAppid() {
        return appid;
    }

    public OrderBody setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getMchId() {
        return mchId;
    }

    public OrderBody setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderBody setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public OrderBody setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public OrderBody setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public OrderBody setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public OrderBody setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public OrderBody setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
        return this;
    }

    public Amount getAmount() {
        return amount;
    }

    public OrderBody setAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    public Payer getPayer() {
        return payer;
    }

    public OrderBody setPayer(Payer payer) {
        this.payer = payer;
        return this;
    }
}
