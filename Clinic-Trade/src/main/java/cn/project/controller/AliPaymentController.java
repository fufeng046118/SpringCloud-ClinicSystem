package cn.project.controller;

import cn.project.config.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付处理控制器
 *
 * @author hduser
 *
 */
@Controller
@RequestMapping("/api")
public class AliPaymentController {
	@Resource
	private AlipayConfig alipayConfig;

	@RequestMapping(value = "/prepay/{orderNo}", method = RequestMethod.GET)
	public String prePay(@PathVariable String orderNo, ModelMap model) {
		try {
				model.addAttribute("hotelName", "入库管理");
				model.addAttribute("roomId", "100000");
				model.addAttribute("count", 1000);
				model.addAttribute("payAmount", 2000);
				model.addAttribute("orderNo","xedyhxiw82665383851651685skjhfufeng");
				return "pay";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}

	}
	@RequestMapping(value = "/pay", method = RequestMethod.POST,produces="application/xml", consumes="application/x-www-form-urlencoded")
	public void pay(
			@RequestParam String WIDout_trade_no,
			@RequestParam String WIDsubject,
			@RequestParam String WIDtotal_amount, HttpServletResponse response) {
		//System.out.println(WIDout_trade_no+","+WIDsubject+","+WIDtotal_amount);
		// 超时时间 可空
		System.out.println(WIDout_trade_no);
		String timeout_express = "2m";
		// 销售产品码 必填
		String product_code = "QUICK_WAP_PAY";
		/**********************/
		// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
		// 调用RSA签名方式
		AlipayClient client = new DefaultAlipayClient(alipayConfig.getUrl(),
				alipayConfig.getAppID(), alipayConfig.getRsaPrivateKey(),
				alipayConfig.getFormat(), alipayConfig.getCharset(),
				alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());
		AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

		// 封装请求支付信息
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setOutTradeNo(WIDout_trade_no);
		model.setSubject(WIDsubject);
		model.setTotalAmount(WIDtotal_amount);
		model.setTimeoutExpress(timeout_express);
		model.setProductCode(product_code);
		alipay_request.setBizModel(model);
		// 设置异步通知地址
		alipay_request.setNotifyUrl(alipayConfig.getNotifyUrl());
		// 设置同步地址
		alipay_request.setReturnUrl(alipayConfig.getReturnUrl());
		// form表单生产
		String form = "";
		try {
			// 调用SDK生成表单
			form = client.pageExecute(alipay_request).getBody();
			System.out.println(form);
			response.setContentType("text/html;charset="
					+ alipayConfig.getCharset());
			response.getWriter().write(form);// 直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导步通知，跟踪支付状态变更
	 */
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public void trackPaymentStatus(HttpServletRequest request,
                                   HttpServletResponse response) {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes(
					"ISO-8859-1"), "UTF-8");
            System.out.println(trade_no);
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status")
					.getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			// 计算得出通知验证结果
			// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
			// publicKey, String charset, String sign_type)
			boolean verify_result = AlipaySignature.rsaCheckV1(params,
					alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), "RSA2");

			if (verify_result) {// 验证成功
				response.getWriter().println("success"); // 请不要修改或删除
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码
				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				//orderService.payFailed(out_trade_no, 1,trade_no);
				response.getWriter().println("fail");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 支付宝页面跳转同步通知页面
	 */
	@RequestMapping(value = "/return", method = RequestMethod.GET)
	public void callback(HttpServletRequest request,
                         HttpServletResponse response) {
		try {
			//获取支付宝GET过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            System.out.println("------------------->"+out_trade_no);
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			boolean verify_result = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), "RSA2");
			
			if(verify_result){//验证成功				
				//String id=orderService.loadItripHotelOrder(out_trade_no).getId().toString();
				//提示支付成功
                //System.out.println("------------------->"+String.format(alipayConfig.getPaymentSuccessUrl(), out_trade_no,id));
				response.sendRedirect(
						String.format(alipayConfig.getPaymentSuccessUrl(),null));
			}else{				
				//提示支付失败
				response.sendRedirect(alipayConfig.getPaymentFailureUrl());
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/index")
	public String index(){
		return "hello-wprd";
	}
}