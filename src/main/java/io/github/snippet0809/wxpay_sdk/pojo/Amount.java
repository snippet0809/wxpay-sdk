package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Amount {

    private Integer total;              // 订单总金额，单位为分
    private String currency;            // CNY：人民币，境内商户号仅支持人民币。
    @JSONField(name = "payer_total")
    private Integer payerTotal;         // 用户支付金额，单位为分。
    @JSONField(name = "payer_currency")
    private String payerCurrency;       // 用户支付币种
    private Integer refund;             // 退款金额，币种的最小单位，只能为整数，不能超过原订单支付金额。

    public Integer getTotal() {
        return total;
    }

    public Amount setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Amount setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Integer getPayerTotal() {
        return payerTotal;
    }

    public Amount setPayerTotal(Integer payerTotal) {
        this.payerTotal = payerTotal;
        return this;
    }

    public String getPayerCurrency() {
        return payerCurrency;
    }

    public Amount setPayerCurrency(String payerCurrency) {
        this.payerCurrency = payerCurrency;
        return this;
    }

    public Integer getRefund() {
        return refund;
    }

    public Amount setRefund(Integer refund) {
        this.refund = refund;
        return this;
    }
}
