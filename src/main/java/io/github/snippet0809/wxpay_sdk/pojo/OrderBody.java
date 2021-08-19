package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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
}
