# wxpay-sdk

## 使用

```java
/**
 * 微信支付工具类
 */
public class WxPayUtil {

    private final SignVerifier signVerifier;
    private final AesDecoder aesDecoder;

    private final WxPayClient wxPayClient;
    private final WxAppPayClient wxAppPayClient;
    private final WxJsapiPayClient wxJsapiPayClient;

    /**
     * App支付和JSAPI支付共用一个商户号
     */
    public WxPayUtil(String appPayAppId, String jsapiPayAppId, String mchId, String serialNo, PrivateKey privateKey, String aesKey) throws WxPayApiException, IOException {
        SignProducer signProducer = new SignProducer(mchId, serialNo, privateKey);
        this.aesDecoder = new AesDecoder(aesKey);
        this.signVerifier = new SignVerifier(signProducer, this.aesDecoder);
        this.wxPayClient = new WxPayClient(signProducer, this.signVerifier);
        this.wxAppPayClient = new WxAppPayClient(appPayAppId, signProducer, this.signVerifier);
        this.wxJsapiPayClient = new WxJsapiPayClient(jsapiPayAppId, signProducer, this.signVerifier);
    }

    /**
     * App支付下单
     */
    public String appPayOrdering(String outTradeNo, String goodsDesc, int total, String notifyUrl) throws WxPayApiException, IOException {
        return wxAppPayClient.simplestOrdering(outTradeNo, goodsDesc, total, notifyUrl);
    }

    /**
     * JSAPI支付下单
     */
    public String jsapiPayOrdering(String outTradeNo, String goodsDesc, int total, String notifyUrl, String openid) throws WxPayApiException, IOException {
        return wxJsapiPayClient.simplestOrdering(outTradeNo, goodsDesc, total, notifyUrl, openid);
    }

    /**
     * 验证签名
     */
    public boolean verifySign(String serialNo, String timestamp, String nonceStr, String body, String signature) {
        return signVerifier.verifySign(serialNo, timestamp, nonceStr, body, signature);
    }

    /**
     * AES解密
     */
    public String decryptToString(String associatedData, String nonce, String ciphertext) throws GeneralSecurityException {
        return aesDecoder.decryptToString(associatedData, nonce, ciphertext);
    }

    /**
     * 退款
     */
    public String simpleRefund(String outTradeNo, String outRefundNo, int total, int refund, String reason) throws WxPayApiException, IOException {
        return wxPayClient.simpleRefund(outTradeNo, outRefundNo, total, refund, reason);
    }
}
```