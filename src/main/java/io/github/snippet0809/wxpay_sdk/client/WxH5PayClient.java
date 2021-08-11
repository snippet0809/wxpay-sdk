package io.github.snippet0809.wxpay_sdk.client;

import com.alibaba.fastjson.JSONObject;
import io.github.snippet0809.wxpay_sdk.constant.WxApi;
import io.github.snippet0809.wxpay_sdk.exception.WxPayApiException;
import io.github.snippet0809.wxpay_sdk.pojo.Amount;
import io.github.snippet0809.wxpay_sdk.pojo.OrderBody;
import io.github.snippet0809.wxpay_sdk.security.SignProducer;
import io.github.snippet0809.wxpay_sdk.security.SignVerifier;

import java.io.IOException;

public class WxH5PayClient extends WxPayClient {

    private final String appid;

    public WxH5PayClient(String appid, SignProducer signProducer, SignVerifier signVerifier) {
        super(signProducer, signVerifier);
        this.appid = appid;
    }

    /**
     * App支付下单
     */
    public String ordering(OrderBody orderBody) throws IOException, WxPayApiException {
        return wxPayHttpClient.post(WxApi.TRANSACTION_H5, JSONObject.toJSONString(orderBody));
    }

    /**
     * 最简单的App支付下单
     */
    public String simplestOrdering(String outTradeNo, String goodsDesc, int total, String notifyUrl) throws WxPayApiException, IOException {
        OrderBody orderBody = new OrderBody();
        orderBody.setAppid(appid);
        orderBody.setMchId(signProducer.getMchId());
        orderBody.setDescription(goodsDesc);
        orderBody.setOutTradeNo(outTradeNo);
        orderBody.setNotifyUrl(notifyUrl);
        Amount amount = new Amount();
        amount.setTotal(total);
        orderBody.setAmount(amount);
        return ordering(orderBody);
    }
}
