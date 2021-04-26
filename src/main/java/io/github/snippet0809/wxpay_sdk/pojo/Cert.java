package io.github.snippet0809.wxpay_sdk.pojo;


import com.alibaba.fastjson.annotation.JSONField;

public class Cert {

    @JSONField(name = "serial_no")
    private String serialNo;
    @JSONField(name = "effective_time")
    private String effectiveTime;
    @JSONField(name = "expire_time")
    private String expireTime;
    @JSONField(name = "encrypt_certificate")
    private EncryptCertificate encryptCertificate;

    public String getSerialNo() {
        return serialNo;
    }

    public Cert setSerialNo(String serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public Cert setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
        return this;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public Cert setExpireTime(String expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public EncryptCertificate getEncryptCertificate() {
        return encryptCertificate;
    }

    public Cert setEncryptCertificate(EncryptCertificate encryptCertificate) {
        this.encryptCertificate = encryptCertificate;
        return this;
    }

    public static class EncryptCertificate {
        private String algorithm;
        private String nonce;
        @JSONField(name = "associated_data")
        private String associatedData;
        private String ciphertext;

        public String getAlgorithm() {
            return algorithm;
        }

        public EncryptCertificate setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public String getNonce() {
            return nonce;
        }

        public EncryptCertificate setNonce(String nonce) {
            this.nonce = nonce;
            return this;
        }

        public String getAssociatedData() {
            return associatedData;
        }

        public EncryptCertificate setAssociatedData(String associatedData) {
            this.associatedData = associatedData;
            return this;
        }

        public String getCiphertext() {
            return ciphertext;
        }

        public EncryptCertificate setCiphertext(String ciphertext) {
            this.ciphertext = ciphertext;
            return this;
        }
    }
}
