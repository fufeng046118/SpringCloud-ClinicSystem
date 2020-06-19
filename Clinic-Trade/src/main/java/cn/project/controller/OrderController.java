package cn.project.controller;

import cn.project.bean.OrderInfo;
import cn.project.service.OrderService;
import cn.project.util.Response;
import cn.project.util.ResponseEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")

public class OrderController {
    @Resource
    OrderService orderService;
    @Value("${spring.test}")
    private String test;

    @GetMapping("/test")
    public String test(){
        return test;
    }

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
