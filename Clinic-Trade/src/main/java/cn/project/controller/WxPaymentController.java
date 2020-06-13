package cn.project.controller;

import cn.project.bean.ItripHotelOrder;
import cn.project.config.MatrixToImageWriterWithLogo;
import cn.project.config.WXPayConfig;
import cn.project.util.HttpResult;
import cn.project.wx.WXPayRequest;
import cn.project.wx.WXPayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zezhong.shang on 17-8-11.
 */
@Controller
@RequestMapping("/api/wxpay/")
public class WxPaymentController {
    //**根据订单号生成二维码**//
    @Resource
    private WXPayConfig wxPayConfig;

    //"客户端提交订单支付请求，对该API的返回结果不用处理，浏览器将自动跳转至微信支付二维码页面。<br><b>请使用普通表单提交，不能使用ajax异步提交。</b>")
    @RequestMapping(value = "/createqccode/{orderNo}", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult createQcCode(@PathVariable String orderNo, HttpServletResponse response) {
        ItripHotelOrder order = null;
        HashMap<String, String> data = new HashMap<String, String>();
        HashMap<String, Object> result = new HashMap<String, Object>();
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
            if (resultCode.equals("SUCCESS")) {
                result.put("codeUrl", r.get("code_url"));
                System.out.println("二维码-->"+r.get("code_url"));
                String icon = WxPaymentController.class.getClassLoader().getResource("coffee_icon.png").getPath();
                BufferedImage bufferedImage = MatrixToImageWriterWithLogo.genBarcode(r.get("code_url"), 512, 512, icon); // 二维码的内容，宽，高，二维码中心的图片地址
                ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
                return HttpResult.ok("",result);
            } else {
                return HttpResult.error("110002", "订单支付异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("110002", "订单运行异常");
        }
    }
    /***
     * ww
     * 微信支付轮询订单，查看订单是否支付成功
     *
     * @param
     * @return
     */
    //订单微信支付刷单程序
    //"前端用户检测订单是否成功的接口<br>如果订单状态为2则代表订单支付成功<b></b>"
    /*@RequestMapping(value = "/queryorderstatus/{orderNo}", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult queryOrderIsSuccess(@PathVariable String orderNo) {
        ItripHotelOrder order = null;
        ItripPersonalHotelOrderVO itripPersonalHotelOrderVO = new ItripPersonalHotelOrderVO();
        try {
            order = orderService.loadItripHotelOrder(orderNo);
            itripPersonalHotelOrderVO.setBookType(order.getBooktype());
            itripPersonalHotelOrderVO.setCreationDate(order.getCreationdate());
            itripPersonalHotelOrderVO.setOrderNo(order.getOrderno());
            itripPersonalHotelOrderVO.setId(order.getId());
            itripPersonalHotelOrderVO.setPayType(order.getPaytype());
            itripPersonalHotelOrderVO.setOrderStatus(order.getOrderstatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpResult.ok("",itripPersonalHotelOrderVO);
    }*/

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public String paymentCallBack(HttpServletRequest request, HttpServletResponse response) {
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
        //return result;
        return "redirect:/api/wxpay/index";
    }

    @GetMapping("/index")
    public String index(){
        return "hello-wprd.html";
    }
}
