package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WakeJsapiPayParam {

    private String appId;
    private String timeStamp;
    private String nonceStr;
    @JSONField(name = "package")
    private String _package;
    private String signType;
    private String paySign;
}
