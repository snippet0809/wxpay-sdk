package io.github.snippet0809.wxpay_sdk.constant;

public interface WxApi {

    String TRANSACTION_APP = "https://api.mch.weixin.qq.com/v3/pay/transactions/app";
    String TRANSACTION_JSAPI = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
    String TRANSACTION_H5 = "https://api.mch.weixin.qq.com/v3/pay/transactions/h5";
    String TRANSACTION_NATIVE = "https://api.mch.weixin.qq.com/v3/pay/transactions/native";

    String REFUND = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";

    String CERT_DOWN = "https://api.mch.weixin.qq.com/v3/certificates";
}
