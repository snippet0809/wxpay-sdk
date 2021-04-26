package io.github.snippet0809.wxpay_sdk.security;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;
import java.util.Random;

public class SignProducer {

    private final String mchId;                     // 商户号
    private final String serialNo;                  // 商户API证书序列号
    private final PrivateKey privateKey;            // 商户API证书私钥


    public SignProducer(String mchId, String serialNo, PrivateKey privateKey) {
        this.mchId = mchId;
        this.serialNo = serialNo;
        this.privateKey = privateKey;
    }

    public String getMchId() {
        return mchId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public String generateSign(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strings) {
            stringBuilder.append(s).append("\n");
        }
        String str = stringBuilder.toString();
        try {
            Signature sha256withRSA = Signature.getInstance("SHA256withRSA");
            sha256withRSA.initSign(privateKey);
            sha256withRSA.update(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(sha256withRSA.sign());
        } catch (Exception e) {
            throw new RuntimeException("使用Sha256withRSA签名失败", e);
        }
    }

    /**
     * 生成32位随机字符串
     */
    public static String generateNonceStr() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            int index = random.nextInt(str.length());
            sb.append(str.charAt(index));
        }
        return sb.toString();
    }
}
