package cn.project.controller;

import cn.project.service.departmentService.DepartmentService;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "科室控制器")
@RequestMapping("department")
public class DepartmentController {
    @Resource
    DepartmentService departmentService;

    @GetMapping("/getAllDepartment")
    @ApiOperation(value = "获取所有科室")
    public Response getAllDepartment(){
        return new Response(ResponseEnum.SUCCESS).setResponseBody(departmentService.getAllDepartment());
    }
}
