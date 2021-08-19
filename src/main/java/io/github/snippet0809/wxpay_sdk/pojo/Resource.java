package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Resource {

    private String algorithm;
    private String ciphertext;
    @JSONField(name = "associated_data")
    private String associatedData;
    @JSONField(name = "original_type")
    private String original_type;
    private String nonce;
}
