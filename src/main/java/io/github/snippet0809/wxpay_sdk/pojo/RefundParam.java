package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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
}
