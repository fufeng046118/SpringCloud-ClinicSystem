package cn.project.controller;

import cn.project.entity.Prescription;
import cn.project.entity.PrescriptionMedicineC;
import cn.project.entity.PrescriptionMedicineX;
import cn.project.entity.PrescriptionMedicineZ;
import cn.project.service.PrescriptionMedicineCService;
import cn.project.service.PrescriptionMedicineXService;
import cn.project.service.PrescriptionMedicineZService;
import cn.project.service.PrescriptionService;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "处方接口")
@RequestMapping("api")
public class APIPrescriptionController {
    @Resource
    BasicDataFeign basicDataFeign;
    @Resource
    PrescriptionService prescriptionService;
    @Resource
    PrescriptionMedicineXService prescriptionMedicineXService;
    @Resource
    PrescriptionMedicineZService prescriptionMedicineZService;
    @Resource
    PrescriptionMedicineCService prescriptionMedicineCService;

    @GetMapping("/getPrescriptionType")
    @ApiOperation(value = "获得所有处方类型")
    public Response getPrescriptionType(){
        return basicDataFeign.getAllPrescriptionType();
    }

    @PostMapping("/addPrescription")
    @ApiOperation(value = "新增处方")
    public Response addPrescription(@RequestBody Prescription prescription){
        prescriptionService.save(prescription);
        return new Response(ResponseEnum.SUCCESS).setResponseBody(prescription.getId());
    }

    @PostMapping("/addPrescriptionMedicineX/{prescriptionId}")
    @ApiOperation(value = "新增处方西药")
    @ApiImplicitParam(name = "prescriptionId",value = "处方ID",example = "1",required = true)
    public Response addPrescriptionMedicineX(@PathVariable Integer prescriptionId,@RequestBody PrescriptionMedicineX prescriptionMedicineX){
        prescriptionMedicineXService.save(prescriptionMedicineX);
        prescriptionMedicineXService.addPrescription_PrescriptionMedicineX(prescriptionId,prescriptionMedicineX.getId());
        return new Response(ResponseEnum.SUCCESS);
    }

    @PostMapping("/addPrescriptionMedicineZ/{prescriptionId}")
    @ApiOperation(value = "新增处方中药")
    @ApiImplicitParam(name = "prescriptionId",value = "处方ID",example = "1",required = true)
    public Response addPrescriptionMedicineZ(@PathVariable Integer prescriptionId, @RequestBody PrescriptionMedicineZ prescriptionMedicineZ){
        prescriptionMedicineZService.save(prescriptionMedicineZ);
        prescriptionMedicineZService.addPrescription_PrescriptionMedicineZ(prescriptionId,prescriptionMedicineZ.getId());
        return new Response(ResponseEnum.SUCCESS);
    }

    @PostMapping("/addPrescriptionMedicineC/{prescriptionId}")
    @ApiOperation(value = "新增处方检查项目")
    @ApiImplicitParam(name = "prescriptionId",value = "处方ID",example = "1",required = true)
    public Response addPrescriptionMedicineC(@PathVariable Integer prescriptionId,@RequestBody PrescriptionMedicineC prescriptionMedicineC){
        prescriptionMedicineCService.save(prescriptionMedicineC);
        prescriptionMedicineCService.addPrescription_PrescriptionMedicineC(prescriptionId,prescriptionMedicineC.getId());
        return new Response(ResponseEnum.SUCCESS);
    }

    @GetMapping("/getAllPrescriptionMedicineC/{id}")
    public Object getAllPrescriptionMedicineC(@PathVariable int id){
        Map<String,Object> map = new HashMap<>();
        map.put("data",prescriptionMedicineCService.getAllPrescriptionMedicineCById(id));
        map.put("code","0");
        return map;
    }

    @GetMapping("/getAllPrescriptionMedicineZ/{id}")
    public Object getAllPrescriptionMedicineZ(@PathVariable int id){
        Map<String,Object> map = new HashMap<>();
        map.put("data",prescriptionMedicineZService.getAllPrescriptionMedicineZById(id));
        map.put("code","0");
        return map;
    }

    @GetMapping("/getAllPrescriptionMedicineX/{id}")
    public Object getAllPrescriptionMedicineX(@PathVariable int id){
        Map<String,Object> map = new HashMap<>();
        map.put("data",prescriptionMedicineXService.getAllPrescriptionMedicineXById(id));
        map.put("code","0");
        return map;
    }
}
