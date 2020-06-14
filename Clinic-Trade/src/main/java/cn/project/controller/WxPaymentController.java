package cn.project.controller;

import cn.project.bean.OrderInfo;
import cn.project.config.WXPayConfig;
import cn.project.service.OrderService;
import cn.project.util.Response;
import cn.project.util.ResponseEnum;
import cn.project.wx.WXPayRequest;
import cn.project.wx.WXPayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/wxpay/")
public class WxPaymentController {
    @Resource
    private WXPayConfig wxPayConfig;
    @Resource
    OrderService orderService;
    @RequestMapping(value = "/createqccode/{orderNo}", method = RequestMethod.GET)
    public Response createQcCode(@PathVariable String orderNo) {
        HashMap<String, String> data = new HashMap<String, String>();
        WXPayRequest wxPayRequest = new WXPayRequest(this.wxPayConfig);
        try {
            data.put("body", "诊所管理系统收费");
            data.put("out_trade_no", orderNo);
            data.put("device_info", "");
            data.put("total_fee", "1");
            data.put("spbill_create_ip", "47.92.146.135");
            data.put("notify_url", " http://mnxzpi.natappfree.cc/api/wxpay/notify");
            Map<String, String> r = wxPayRequest.unifiedorder(data);
            String resultCode = r.get("result_code");
            System.out.println(r.get("code_url"));
            if (resultCode.equals("SUCCESS")) {
                return new Response(ResponseEnum.SUCCESS).setResponseBody(r.get("code_url"));
            } else {
                return new Response(ResponseEnum.ERROR).setResponseBody("订单支付异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.ERROR).setResponseBody("订单运行异常");
        }
    }
    @RequestMapping(value = "/queryorderstatus/{orderNo}", method = RequestMethod.GET)
    public Response queryOrderIsSuccess(@PathVariable String orderNo) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderNo",orderNo);
        OrderInfo order = orderService.getOne(queryWrapper);
        if(order != null && order.getStatus() == 1){
            return new Response(ResponseEnum.SUCCESS).setResponseBody("支付成功");
        }else {
            return new Response(ResponseEnum.SUCCESS).setResponseBody("支付失败");
        }
    }

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public Map<String, String> paymentCallBack(HttpServletRequest request) {
        WXPayRequest wxPayRequest = new WXPayRequest(this.wxPayConfig);
        Map<String, String> result = new HashMap<String, String>();
        Map<String, String> params = null;
        try {
            InputStream inputStream;
            StringBuffer sb = new StringBuffer();
            inputStream = request.getInputStream();
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
            in.close();
            inputStream.close();
            params = WXPayUtil.xmlToMap(sb.toString());
            System.out.println("1.notify-params>>>>>>>>>>>:" + params);
            boolean flag = wxPayRequest.isResponseSignatureValid(params);
            System.out.println("2.notify-flag:" + flag);
            if (flag) {
                String returnCode = params.get("return_code");
                System.out.println("3.returnCode:" + returnCode);
                if (returnCode.equals("SUCCESS")) {
                    String transactionId = params.get("transaction_id");
                    String outTradeNo = params.get("out_trade_no");
                    System.out.println("4.订单：" + outTradeNo + " 交易完成" + ">>>" + transactionId);
                    QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("orderNo",outTradeNo);
                    OrderInfo order = orderService.getOne(queryWrapper);
                    if(order != null && order.getStatus() != 1){
                        order.setStatus(1);
                        orderService.updateById(order);
                    }
                } else {
                    result.put("return_code", "FAIL");
                    result.put("return_msg", "支付失败");
                }
            } else {
                result.put("return_code", "FAIL");
                result.put("return_msg", "签名失败");
                System.out.println("签名验证失败>>>>>>>>>>>>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
