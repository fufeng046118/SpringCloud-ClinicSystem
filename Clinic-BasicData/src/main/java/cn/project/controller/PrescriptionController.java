package cn.project.controller;

import cn.project.service.prescriptionService.PrescriptionService;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "处方控制器")
@RequestMapping("prescription")
public class PrescriptionController {
    @Resource
    PrescriptionService prescriptionService;

    @GetMapping("getAllPrescriptionType")
    @ApiOperation(value = "获取所有处方类型")
    public Response getAllPrescriptionType(){
        return new Response(ResponseEnum.SUCCESS).setResponseBody(prescriptionService.getAllPrescriptionType());
    }
}
