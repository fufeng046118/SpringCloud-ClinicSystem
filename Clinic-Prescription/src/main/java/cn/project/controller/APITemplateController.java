package cn.project.controller;

import cn.project.utils.HttpClientHelper;
import cn.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "负责调用模板接口")
@RequestMapping("/api")
public class APITemplateController {
    @Resource
    BasicDataFeign basicDataFeign;
    @ApiOperation(value = "根据条件查询所有模板并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "prescriptionTypeId",value = "处方类型ID(表示该药品属于(西药、中药、检查项目中的哪一种))",defaultValue = "1"),
            @ApiImplicitParam(paramType = "query",name = "templatePermission",value = "模板权限(0:私有,1:公有)",defaultValue = "1"),
            @ApiImplicitParam(paramType = "query",name = "templateNoOrName",value = "模板名称或拼音",defaultValue = "感冒处方模板"),
            @ApiImplicitParam(paramType = "query",name = "pageNo",value = "当前页",required = true,defaultValue = "1"),
            @ApiImplicitParam(paramType = "query",name = "pageSize",value = "每页显示大小",required = true,defaultValue = "2")
    })
    @PostMapping("/getAllTemplate")
    public Response getAllTemplate(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "2") Integer pageSize, @RequestParam(required = false) Integer prescriptionTypeId, @RequestParam(required = false) Integer templatePermission, @RequestParam(required = false) String templateNoOrName){
        return basicDataFeign.getAllTemplate(pageNo, pageSize, prescriptionTypeId, templatePermission, templateNoOrName);
    }

    @GetMapping("/getTemplateDetails/{id}")
    @ApiOperation(value = "根据模板ID获取模板下的处方详情")
    @ApiImplicitParam(paramType = "query",value = "模板ID",name = "id",required = true,defaultValue = "1")
    public Response getTemplateDetails(@PathVariable Integer id){
        return basicDataFeign.getTemplateById(id);
    }

}
