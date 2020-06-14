package cn.project.controller;

import cn.project.entity.Order;
import cn.project.utils.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/trade")
public class APITradeController {
    @Resource
    TradeFeign tradeFeign;
    @GetMapping("/api/wxpay/createqccode/{orderNo}")
    public Response createQcCode(@PathVariable String orderNo){
        return tradeFeign.createQcCode(orderNo);
    }

    @GetMapping("/api/wxpay/queryorderstatus/{orderNo}")
    public Response queryOrderStatus(@PathVariable String orderNo){
        return tradeFeign.queryOrderIsSuccess(orderNo);
    }

    @PostMapping("/order/addOrder")
    public Response addOrder(@RequestBody Order order){
        return tradeFeign.addOrder(order);
    }

    @GetMapping("/api/prepay/{orderNo}")
    public void prepay(@PathVariable String orderNo){
        tradeFeign.prePay(orderNo);
    }
}
