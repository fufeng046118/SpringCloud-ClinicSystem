package cn.project.controller;

import cn.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "负责调用科室控住器")
@RequestMapping("api")
public class APIDepartmentController {
    @Resource
    BasicDataFeign basicDataFeign;
    @GetMapping("/getAllDepartment")
    @ApiOperation(value = "获得所有的科室信息")
    public Response getAdditionalFees(){
        return basicDataFeign.getAllDepartment();
    }
}
