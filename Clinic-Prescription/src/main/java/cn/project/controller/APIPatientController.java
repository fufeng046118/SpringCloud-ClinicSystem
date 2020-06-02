package cn.project.controller;

import cn.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "负责调用患者控住器")
@RequestMapping("api")
public class APIPatientController {
    @Resource
    BasicDataFeign basicDataFeign;

    @GetMapping("/getPatientById/{id}")
    @ApiOperation(value = "根据ID查询患者信息")
    @ApiImplicitParam(value = "患者ID",name = "id",required = true,defaultValue = "1")
    public Response getPatientById(@PathVariable Integer id){
        return basicDataFeign.getPatientById(id);
    }

    @GetMapping("/getAllPatient")
    @ApiOperation(value = "查询所有患者信息")
    public Response getAllPatient(){
        return basicDataFeign.getAllPatient();
    }
}
