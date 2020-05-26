package cn.project.controller;

import cn.project.service.medicalAdviceService.MedicalAdviceService;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "医嘱控制器")
@RequestMapping("medicalAdvice")
public class MedicalAdviceController {
    @Resource
    MedicalAdviceService medicalAdviceService;

    @GetMapping("getAllMedicalAdvice")
    @ApiOperation(value = "获取所有医嘱")
    public Response getAllMedicalAdvice(){
        return new Response(ResponseEnum.SUCCESS).setResponseBody(medicalAdviceService.getAllMedicalAdvice());
    }

    @GetMapping("addMedicalAdvice")
    @ApiOperation(value = "新增医嘱")
    public Response addMedicalAdvice(Integer prescriptionId,String medicalAdvice){
        medicalAdvice =medicalAdvice .replace("\"", "");
        if(prescriptionId!=null&&medicalAdvice!=null){
            String[] str = medicalAdvice.split(",");
            for (int i = 0;i<str.length;i++){
                medicalAdviceService.addMedicalAdvice(prescriptionId,Integer.parseInt(str[i]));
            }
        }
        return new Response(ResponseEnum.SUCCESS);
    }
}
