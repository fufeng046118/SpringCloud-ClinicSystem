package cn.project.controller;

import cn.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URLEncoder;

@RestController
@Api(tags = "负责调用医嘱控制器")
@RequestMapping("api")
public class APIMedicalAdviceController {
    @Resource
    BasicDataFeign basicDataFeign;

    @GetMapping("getAllMedicalAdvice")
    @ApiOperation(value = "获取所有医嘱信息")
    public Response getAllMedicalAdvice(){
        return basicDataFeign.getAllMedicalAdvice();
    }

    @PostMapping("addMedicalAdvice")
    @ApiOperation(value = "新增医嘱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prescriptionId",value = "处方ID",required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "medicalAdvice",value = "医嘱(多个医嘱ID,例如1,2,3)",required = true,defaultValue = "1,2,3")
    })
    public Response addDiagnosis(@RequestParam Integer prescriptionId,@RequestParam String medicalAdvice){
        medicalAdvice = URLEncoder.encode(medicalAdvice);
        return basicDataFeign.addMedicalAdvice(prescriptionId, medicalAdvice);
    }
}
