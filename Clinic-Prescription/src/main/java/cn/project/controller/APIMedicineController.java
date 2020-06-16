package cn.project.controller;

import cn.project.utils.HttpClientHelper;
import cn.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api")
@Api(tags = "负责调用药品控制器")
public class APIMedicineController {
    @Resource
    MedicineFeign medicineFeign;

    @ApiOperation(value = "根据条件查询所有药品并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "prescriptionTypeId",value = "处方类型ID(表示该药品属于(西药、中药、检查项目中的哪一种))",required = true,defaultValue = "2"),
            @ApiImplicitParam(paramType = "query",name = "medicineTypeId",value = "药品类型ID",defaultValue = "6"),
            @ApiImplicitParam(paramType = "query",name = "nameOrPinYin",value = "药品名称或拼音",defaultValue = "badou"),
            @ApiImplicitParam(paramType = "query",name = "page",value = "当前页",required = true,defaultValue = "1"),
            @ApiImplicitParam(paramType = "query",name = "limit",value = "每页显示大小",required = true,defaultValue = "2")
    })
    @PostMapping("getMedicine")
    public Object getMedicine(@RequestParam(defaultValue = "1") Integer prescriptionTypeId, @RequestParam(required = false) Integer medicineTypeId, @RequestParam(required = false) String nameOrPinYin, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "2") Integer limit){
        return medicineFeign.getMedicines(prescriptionTypeId, medicineTypeId, nameOrPinYin, page, limit).getResponseBody();
    }

    @GetMapping("getAllMedicineType/{prescriptionTypeId}")
    @ApiOperation(value = "负责调用基本数据模块中的药品控制器(根据条件查询所有药品类型)")
    @ApiImplicitParam(name = "prescriptionTypeId",value = "处方类型ID(西药、中药、检查项目)",required = true,defaultValue = "1")
    public Response getAllMedicineType(@PathVariable Integer prescriptionTypeId){
        return medicineFeign.getMedicineType(prescriptionTypeId);
    }

    @GetMapping("/report")
    @ApiOperation(value = "打印报表")
    public void report(HttpServletResponse response){
        try {
            response.sendRedirect("http://localhost:8083/medicine/report");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getMedicineUsage")
    @ApiOperation(value = "获取所有药品用法")
    public Response getMedicineUsage(){
        return medicineFeign.getMedicineUsage();
    }

    @PostMapping("/updateMedicineStock/{id}")
    public Response updateMedicineStock(@PathVariable int id,@RequestParam int stock){
        medicineFeign.updateMedicineStock(id, stock);
        return new Response();
    }
}
