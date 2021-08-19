package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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
}
