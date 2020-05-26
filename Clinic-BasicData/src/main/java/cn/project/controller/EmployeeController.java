package cn.project.controller;

import cn.project.service.employeeService.EmployeeService;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "员工控制器")
@RequestMapping("employee")
public class EmployeeController {
    @Resource
    EmployeeService employeeService;

    @GetMapping("getAllEmployee")
    @ApiOperation(value = "获取该科室下的所有员工")
    public Response getAllEmployee(Integer id){
        return new Response(ResponseEnum.SUCCESS).setResponseBody(employeeService.getAllEmployee(id));
    }
}
