package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class RefundParam {

    @JSONField(name = "transaction_id")
    private String transactionId;
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "out_refund_no")
    private String outRefundNo;
    private String reason;
    @JSONField(name = "notify_url")
    private String notifyUrl;
    @JSONField(name = "funds_account")
    private String fundsAccount;
    private Amount amount;

    public String getTransactionId() {
        return transactionId;
    }

    public RefundParam setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public RefundParam setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public RefundParam setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public RefundParam setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public RefundParam setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public String getFundsAccount() {
        return fundsAccount;
    }

    public RefundParam setFundsAccount(String fundsAccount) {
        this.fundsAccount = fundsAccount;
        return this;
    }

    public Amount getAmount() {
        return amount;
    }

    public RefundParam setAmount(Amount amount) {
        this.amount = amount;
        return this;
    }
}
