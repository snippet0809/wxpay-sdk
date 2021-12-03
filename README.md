# wxpay-sdk

## 一、导入依赖

#### 1、添加Github Packages仓库

```xml

<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/snippet0809/wxpay-sdk</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

注意，若在~/.m2/setting.xml中配置了&lt;mirrorOf>*<mirrorOf&gt;的镜像，会被误伤的

#### 2、导入jar包

```xml

<dependency>
    <groupId>io.github.snippet0809</groupId>
    <artifactId>wxpay-sdk</artifactId>
    <version>${wxpay-sdk.version}</version>
</dependency>
```

#### 3、配置Github Token

当前即使从Github Packages下载public包，依然需要token， 故需要在maven的setting.xml
（默认路径为~/.m2/setting.xml或IntelliJ IDEA右键项目 -> maven -> Open 'setting.xml'）中添加github认证配置

```xml

<servers>
    <server>
        <id>github</id>
        <username>USERNAME</username>
        <password>TOKEN</password>
    </server>
</servers>

```

## 二、使用

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