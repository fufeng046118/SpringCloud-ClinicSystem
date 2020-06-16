package cn.project.controller;

import cn.project.config.CreateReport;
import cn.project.entity.CheckItem;
import cn.project.entity.Medicine;
import cn.project.service.MedicineService;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@Api(tags = "药品控制器")
@RequestMapping("medicine")
public class MedicineController {
    @Resource
    MedicineService medicineService;

    @PostMapping("getMedicines")
    @ApiOperation(value = "获取所有药品",notes = "根据药品类型、药瓶名称/拼音来筛选药品并进行分页显示")
    public Response getMedicines (Integer prescriptionTypeId,Integer medicineTypeId,String nameOrPinYin,Integer pageNo,Integer pageSize){
        Map<String,Object> map = new HashMap<>();
        PageInfo<Medicine> pageInfo = new PageInfo<>();
        PageInfo<CheckItem> pageInfo1 = new PageInfo<>();
        if(prescriptionTypeId == 1 || prescriptionTypeId == 2){
            pageInfo = medicineService.getAllMedicine(prescriptionTypeId,medicineTypeId,nameOrPinYin,pageNo,pageSize);
            map.put("data",pageInfo.getList());
            map.put("code","0");
            map.put("count",pageInfo.getTotal());
        }else {
            pageInfo1 = medicineService.getAllCheckItem(medicineTypeId,nameOrPinYin,pageNo,pageSize);
            map.put("data",pageInfo1.getList());
            map.put("code","0");
            map.put("count",pageInfo1.getTotal());
        }
        return new Response(ResponseEnum.SUCCESS).setResponseBody(map);
    }

    @GetMapping(value = "/getMedicineType/{prescriptionTypeId}")
    @ApiOperation(value = "获取所有药品类型")
    public Response getMedicineType(@PathVariable("prescriptionTypeId") Integer prescriptionTypeId){
        if(prescriptionTypeId == 1 || prescriptionTypeId == 2){
            System.out.println(prescriptionTypeId);
            return new Response(ResponseEnum.SUCCESS).setResponseBody(medicineService.getAllMedicineType());
        }else{
            return new Response(ResponseEnum.SUCCESS).setResponseBody(medicineService.getAllCheckItemType());
        }
    }

    @GetMapping("/report")
    public void report(HttpServletResponse response){
        CreateReport report = new CreateReport();
        List<String> headList = Arrays.asList("序号","药品名称","库存","采购成本");
        List<List<String>> dataList = new ArrayList<>();
        List<Medicine> medicineList = medicineService.getAllMedicine(null,null,null,0,30).getList();
        for (int i = 0; i < medicineList.size(); i++) {
            dataList.add(Arrays.asList("" + i+1,medicineList.get(i).getMedicineName(),medicineList.get(i).getStock() +"",medicineList.get(i).getPurchasePrice()+""));
        }
        report.createWorkBook(null, "a", headList, dataList, response, "报表测试.xls");
    }

    @GetMapping("/getMedicineUsage")
    public Response getMedicineUsage(){
        return new Response(ResponseEnum.SUCCESS).setResponseBody(medicineService.getMedicineUsage());
    }

    @PostMapping("/abc")
    public Response abc(String name){
        return new Response(ResponseEnum.SUCCESS).setResponseBody("hello:"+name);
    }

    @PostMapping("/updateMedicineStock/{id}")
    public Response updateMedicineStock(@PathVariable int id,@RequestParam int stock){
        medicineService.updateMedicine(id,stock);
        return new Response(ResponseEnum.SUCCESS);
    }
}
