package cn.project.controller;

import cn.project.entity.Prescription_AdditionalFees;
import cn.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "负责调用附加费用控住器")
@RequestMapping("api")
public class APIAdditionalFeesController {
    @Resource
    BasicDataFeign dataFeign;
    @GetMapping("/getAdditionalFees")
    @ApiOperation(value = "获取所有附加费用")
    public Response getAdditionalFees(){
        return dataFeign.getAllAdditionalFees();
    }

    @PostMapping("/addAdditionalFees")
    @ApiOperation(value = "新增附加费用")
    public Response addAdditionalFees(@RequestBody Prescription_AdditionalFees prescription_additionalFees){
        return dataFeign.addAdditionalFees(prescription_additionalFees);
    }
}
