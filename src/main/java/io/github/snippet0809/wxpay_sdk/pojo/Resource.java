package io.github.snippet0809.wxpay_sdk.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Resource {

    private String algorithm;
    private String ciphertext;
    @JSONField(name = "associated_data")
    private String associatedData;
    @JSONField(name = "original_type")
    private String original_type;
    private String nonce;

    public String getAlgorithm() {
        return algorithm;
    }

    public Resource setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public Resource setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
        return this;
    }

    public String getAssociatedData() {
        return associatedData;
    }

    public Resource setAssociatedData(String associatedData) {
        this.associatedData = associatedData;
        return this;
    }

    public String getOriginal_type() {
        return original_type;
    }

    public Resource setOriginal_type(String original_type) {
        this.original_type = original_type;
        return this;
    }

    public String getNonce() {
        return nonce;
    }

    public Resource setNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }
}
