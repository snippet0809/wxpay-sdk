package io.github.snippet0809.wxpay_sdk.client;

import com.alibaba.fastjson.JSONObject;
import io.github.snippet0809.wxpay_sdk.constant.WxApi;
import io.github.snippet0809.wxpay_sdk.exception.WxPayApiException;
import io.github.snippet0809.wxpay_sdk.httpclient.WxPayHttpClient;
import io.github.snippet0809.wxpay_sdk.pojo.Amount;
import io.github.snippet0809.wxpay_sdk.pojo.RefundParam;
import io.github.snippet0809.wxpay_sdk.security.SignProducer;
import io.github.snippet0809.wxpay_sdk.security.SignVerifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WxPayClient {

    protected final SignProducer signProducer;          // 当前类和子类需要这个对象计算签名
    protected final WxPayHttpClient wxPayHttpClient;    // 当前类和子类需要这个对象给微信API发送http请求

    public WxPayClient(SignProducer signProducer, SignVerifier signVerifier) {
        this.signProducer = signProducer;
        this.wxPayHttpClient = new WxPayHttpClient(signProducer, signVerifier);
    }

    /**
     * 申请退款
     */
    public String refund(RefundParam refundParam) throws IOException, WxPayApiException {
        return wxPayHttpClient.post(WxApi.REFUND, JSONObject.toJSONString(refundParam));
    }

    /**
     * 简单的退款
     */
    public String simpleRefund(String outTradeNo, String outRefundNo, int total, int refund, String reason) throws WxPayApiException, IOException {
        RefundParam refundParam = new RefundParam();
        refundParam.setOutTradeNo(outTradeNo);
        refundParam.setOutRefundNo(outRefundNo);
        Amount amount = new Amount();
        amount.setTotal(total);
        amount.setRefund(refund);
        amount.setCurrency("CNY");
        refundParam.setAmount(amount);
        refundParam.setReason(reason);
        return refund(refundParam);
    }

    /**
     * 根据transactionId查询订单
     */
    public String getTransactionById(String transactionId) throws WxPayApiException, IOException {
        String url = WxApi.GET_TRANSACTION_BY_ID.replace("{transaction_id}", transactionId).replace("{mchid}", signProducer.getMchId());
        return wxPayHttpClient.get(url);
    }

    /**
     * 根据商户订单号查询
     */
    public String getTransactionByOutTradeNo(String outTradeNo) throws WxPayApiException, IOException {
        String url = WxApi.GET_TRANSACTION_BY_OUT_TRADE_NO.replace("{out_trade_no}", outTradeNo).replace("{mchid}", signProducer.getMchId());
        return wxPayHttpClient.get(url);
    }

    /**
     * 关闭订单
     */
    public String closeTransaction(String outTradeNo) throws WxPayApiException, IOException {
        String url = WxApi.CLOSE_TRANSACTION.replace("{out_trade_no}", outTradeNo);
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("mchid", signProducer.getMchId());
        return wxPayHttpClient.post(url, JSONObject.toJSONString(bodyMap));
    }
}
