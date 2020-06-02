package cn.project.controller;

import cn.project.utils.HttpClientHelper;
import cn.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URLEncoder;

@RestController
@Api(tags = "负责调用诊断控制器")
@RequestMapping("api")
public class APIDiagnosisTypeController {
    @Resource
    BasicDataFeign basicDataFeign;

    @GetMapping("getAllDiagnosisType")
    @ApiOperation(value = "获得所有的诊断信息")
    public Response getAllDiagnosisType(){
        return basicDataFeign.getAllDiagnosisType();
    }

    @PostMapping("addDiagnosis")
    @ApiOperation(value = "新增诊断信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prescriptionId",value = "处方ID",required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "diagnosisType",value = "诊断(多个诊断ID,例如1,2,3)",required = true,defaultValue = "1,2,3")
    })
    public Response addDiagnosis(@RequestParam Integer prescriptionId, @RequestParam String diagnosisType){
        diagnosisType = URLEncoder.encode(diagnosisType);
        return basicDataFeign.addDiagnosisType(prescriptionId, diagnosisType);
    }
}
