package io.github.snippet0809.wxpay_sdk.client;

import com.alibaba.fastjson.JSONObject;
import io.github.snippet0809.wxpay_sdk.constant.WxApi;
import io.github.snippet0809.wxpay_sdk.exception.WxPayApiException;
import io.github.snippet0809.wxpay_sdk.pojo.Amount;
import io.github.snippet0809.wxpay_sdk.pojo.OrderBody;
import io.github.snippet0809.wxpay_sdk.pojo.OrderResult;
import io.github.snippet0809.wxpay_sdk.pojo.WakeAppPayParam;
import io.github.snippet0809.wxpay_sdk.security.SignProducer;
import io.github.snippet0809.wxpay_sdk.security.SignVerifier;

import java.io.IOException;

public class WxAppPayClient extends WxPayClient {

    private final String appid;

    public WxAppPayClient(String appid, SignProducer signProducer, SignVerifier signVerifier) {
        super(signProducer, signVerifier);
        this.appid = appid;
    }

    /**
     * App支付下单
     */
    public String ordering(OrderBody orderBody) throws IOException, WxPayApiException {
        // 一、请求微信下单API
        String res = wxPayHttpClient.post(WxApi.TRANSACTION_APP, JSONObject.toJSONString(orderBody));
        OrderResult orderResult = JSONObject.parseObject(res, OrderResult.class);
        // 二、构建调起App支付的参数
        WakeAppPayParam wakeAppPayParam = new WakeAppPayParam();
        wakeAppPayParam.setAppid(appid);
        wakeAppPayParam.setPartnerId(signProducer.getMchId());
        wakeAppPayParam.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));
        wakeAppPayParam.setNonceStr(SignProducer.generateNonceStr());
        wakeAppPayParam.set_package("Sign=WXPay");
        wakeAppPayParam.setPrepayId(orderResult.getPrepayId());
        // 三、加签
        String sign = signProducer.generateSign(wakeAppPayParam.getAppid(), wakeAppPayParam.getTimestamp(), wakeAppPayParam.getNonceStr(), wakeAppPayParam.getPrepayId());
        wakeAppPayParam.setSign(sign);
        return JSONObject.toJSONString(wakeAppPayParam);
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
