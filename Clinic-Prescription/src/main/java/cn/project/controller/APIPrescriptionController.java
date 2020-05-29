package cn.project.controller;

import cn.project.entity.Prescription;
import cn.project.entity.PrescriptionMedicineC;
import cn.project.entity.PrescriptionMedicineX;
import cn.project.entity.PrescriptionMedicineZ;
import cn.project.service.PrescriptionService;
import cn.project.utils.HttpClientHelper;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "处方接口")
@RequestMapping("api")
public class APIPrescriptionController {
    @Resource
    BasicDataFeign basicDataFeign;
    @Resource
    PrescriptionService prescriptionService;

    @GetMapping("/getPrescriptionType")
    @ApiOperation(value = "负责调用基本数据模块中的处方控制器(获得所有处方类型)")
    public Response getPrescriptionType(){
        return basicDataFeign.getAllPrescriptionType();
    }

    @PostMapping("/addPrescription")
    @ApiOperation(value = "新增处方")
    public Response addPrescription(@RequestBody Prescription prescription){
        prescriptionService.addPrescription(prescription);
        return new Response(ResponseEnum.SUCCESS).setResponseBody(prescription.getId());
    }

    @PostMapping("/addPrescriptionMedicineX/{prescriptionId}")
    @ApiOperation(value = "新增处方西药")
    public Response addPrescriptionMedicineX(@PathVariable Integer prescriptionId,@RequestBody PrescriptionMedicineX prescriptionMedicineX){
        prescriptionService.addPrescriptionMedicineX(prescriptionMedicineX);
        prescriptionService.addPrescription_PrescriptionMedicineX(prescriptionId,prescriptionMedicineX.getId());
        return new Response(ResponseEnum.SUCCESS);
    }

    @PostMapping("/addPrescriptionMedicineZ/{prescriptionId}")
    @ApiOperation(value = "新增处方中药")
    public Response addPrescriptionMedicineZ(@PathVariable Integer prescriptionId, @RequestBody PrescriptionMedicineZ prescriptionMedicineZ){
        prescriptionService.addPrescriptionMedicineZ(prescriptionMedicineZ);
        prescriptionService.addPrescription_PrescriptionMedicineZ(prescriptionId,prescriptionMedicineZ.getId());
        return new Response(ResponseEnum.SUCCESS);
    }

    @PostMapping("/addPrescriptionMedicineC/{prescriptionId}")
    @ApiOperation(value = "新增处方检查项目")
    public Response addPrescriptionMedicineC(@PathVariable Integer prescriptionId,@RequestBody PrescriptionMedicineC prescriptionMedicineC){
        prescriptionService.addPrescriptionMedicineC(prescriptionMedicineC);
        prescriptionService.addPrescription_PrescriptionMedicineC(prescriptionId,prescriptionMedicineC.getId());
        return new Response(ResponseEnum.SUCCESS);
    }

}
