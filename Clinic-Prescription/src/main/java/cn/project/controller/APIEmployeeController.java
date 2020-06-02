package cn.project.controller;

import cn.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "负责调用员工控住器")
@RequestMapping("api")
public class APIEmployeeController {
    @Resource
    BasicDataFeign basicDataFeign;

    @GetMapping("/getAllEmployee/{id}")
    @ApiOperation(value = "根据科室ID获取该科室下的员工")
    @ApiImplicitParam(name = "id",value = "科室ID",required = true,defaultValue = "1")
    public Response getAllEmployee(@PathVariable Integer id){
        return basicDataFeign.getAllEmployee(id);
    }
}
