package io.github.snippet0809.wxpay_sdk.client;

import com.alibaba.fastjson.JSONObject;
import io.github.snippet0809.wxpay_sdk.constant.WxApi;
import io.github.snippet0809.wxpay_sdk.exception.WxPayApiException;
import io.github.snippet0809.wxpay_sdk.pojo.*;
import io.github.snippet0809.wxpay_sdk.security.SignProducer;
import io.github.snippet0809.wxpay_sdk.security.SignVerifier;

import java.io.IOException;

public class WxJsapiPayClient extends WxPayClient {

    private final String appid;

    public WxJsapiPayClient(String appid, SignProducer signProducer, SignVerifier signVerifier) {
        super(signProducer, signVerifier);
        this.appid = appid;
    }

    /**
     * JSAPI支付下单
     */
    public String ordering(OrderBody orderBody) throws IOException, WxPayApiException {
        // 一、请求微信下单API
        String res = wxPayHttpClient.post(WxApi.TRANSACTION_JSAPI, JSONObject.toJSONString(orderBody));
        OrderResult orderResult = JSONObject.parseObject(res, OrderResult.class);
        // 二、构建调起App支付的参数
        WakeJsapiPayParam wakeJsapiPayParam = new WakeJsapiPayParam();
        wakeJsapiPayParam.setAppId(appid);
        wakeJsapiPayParam.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
        wakeJsapiPayParam.setNonceStr(SignProducer.generateNonceStr());
        wakeJsapiPayParam.setSignType("RSA");
        wakeJsapiPayParam.set_package("prepay_id=" + orderResult.getPrepayId());
        // 三、加签
        String sign = signProducer.generateSign(wakeJsapiPayParam.getAppId(), wakeJsapiPayParam.getTimeStamp(), wakeJsapiPayParam.getNonceStr(), wakeJsapiPayParam.get_package());
        wakeJsapiPayParam.setPaySign(sign);
        return JSONObject.toJSONString(wakeJsapiPayParam);
    }

    /**
     * 最简单的JSAPI支付
     */
    public String simplestOrdering(String outTradeNo, String goodsDesc, int total, String notifyUrl, String openid) throws WxPayApiException, IOException {
        OrderBody orderBody = new OrderBody();
        orderBody.setAppid(appid);
        orderBody.setMchId(signProducer.getMchId());
        orderBody.setDescription(goodsDesc);
        orderBody.setOutTradeNo(outTradeNo);
        orderBody.setNotifyUrl(notifyUrl);
        Amount amount = new Amount();
        amount.setTotal(total);
        orderBody.setAmount(amount);
        Payer payer = new Payer();
        payer.setOpenid(openid);
        orderBody.setPayer(payer);
        return ordering(orderBody);
    }
}
