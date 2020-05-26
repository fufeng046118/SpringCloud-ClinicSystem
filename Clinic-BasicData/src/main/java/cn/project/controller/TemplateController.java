package cn.project.controller;


import cn.project.entity.Template;
import cn.project.service.templateService.TemplateService;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "处方模板控制器")
@RestController
@RequestMapping("/template")
public class TemplateController {
    @Resource
    TemplateService templateService;
    @ApiOperation(value = "获取所有模板",notes = "根据处方类别、处方权限、模板编码/模板名称来筛选模板并进行分页显示")
    @GetMapping("/getAllTemplate")
    public Response getAllTemplate(Integer pageNo, Integer pageSize, Integer prescriptionTypeId, Integer templatePermission, String templateNoOrName){
        Map<String,Object> map = new HashMap<>();
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        map.put("prescriptionTypeId",prescriptionTypeId);
        map.put("templatePermission",templatePermission);
        map.put("templateNoOrName",templateNoOrName);
        return new Response(ResponseEnum.SUCCESS).setResponseBody(templateService.getAllTemplate(map));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "获取模板下的处方药品",notes = "根据模板id去获取处方药品信息")
    public Response getTemplateById(@PathVariable Integer id){
        Template template = templateService.getTemplateById(id);
        if(template.getPrescriptionTypeId() == 1){
            return new Response(ResponseEnum.SUCCESS).setResponseBody(templateService.getPrescriptionMedicineX(id));
        }else if(template.getPrescriptionTypeId() == 2){
            return new Response(ResponseEnum.SUCCESS).setResponseBody(templateService.getPrescriptionMedicineZ(id));
        }else{
            return new Response(ResponseEnum.SUCCESS).setResponseBody(templateService.getPrescriptionMedicineC(id));
        }
    }
}
