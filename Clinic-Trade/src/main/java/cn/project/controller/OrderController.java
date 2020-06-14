package cn.project.controller;

import cn.project.bean.OrderInfo;
import cn.project.service.OrderService;
import cn.project.util.Response;
import cn.project.util.ResponseEnum;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {
    @Resource
    OrderService orderService;

    @PostMapping("/addOrder")
    public Response addOrder(@RequestBody OrderInfo order){
        if(orderService.save(order)){
            System.out.println(order.getId());
            return new Response(ResponseEnum.SUCCESS).setResponseBody(order.getId());
        }else {
            return new Response(ResponseEnum.ERROR).setResponseBody("新增失败");
        }
    }
}
