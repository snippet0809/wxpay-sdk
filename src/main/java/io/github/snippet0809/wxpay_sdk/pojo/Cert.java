package io.github.snippet0809.wxpay_sdk.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Cert {

    @JSONField(name = "serial_no")
    private String serialNo;
    @JSONField(name = "effective_time")
    private String effectiveTime;
    @JSONField(name = "expire_time")
    private String expireTime;
    @JSONField(name = "encrypt_certificate")
    private EncryptCertificate encryptCertificate;

    @Data
    @Accessors(chain = true)
    public static class EncryptCertificate {
        private String algorithm;
        private String nonce;
        @JSONField(name = "associated_data")
        private String associatedData;
        private String ciphertext;
    }
}
