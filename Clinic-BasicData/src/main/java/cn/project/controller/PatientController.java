package cn.project.controller;

import cn.project.service.patientService.PatientService;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "患者控制器")
@RequestMapping("patient")
public class PatientController {
    @Resource
    PatientService patientService;

    @GetMapping("/getPatientById")
    @ApiOperation(value = "根据ID获取患者信息")
    public Response getAllAdditionalFees(Integer id){
        return new Response(ResponseEnum.SUCCESS).setResponseBody(patientService.getPatientById(id));
    }

    @GetMapping("/getAllPatient")
    @ApiOperation(value = "获取所有患者信息")
    public Response getAllPatient(){
        return new Response(ResponseEnum.SUCCESS).setResponseBody(patientService.getAllPatient());
    }
}
