package cn.project.controller;

import cn.project.config.BasicDataFeignFallback;
import cn.project.config.FeignConfig;
import cn.project.entity.Prescription_AdditionalFees;
import cn.project.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "Clinic-BasicData",fallback = BasicDataFeignFallback.class,configuration = {FeignConfig.class})
public interface BasicDataFeign {
    //附加费用
    @GetMapping("additionalFees/getAllAdditionalFees")
    public Response getAllAdditionalFees();

    @PostMapping("additionalFees/addAdditionalFees")
    public Response addAdditionalFees(@RequestBody Prescription_AdditionalFees prescription_additionalFees);


    //科室
    @GetMapping("department/getAllDepartment")
    public Response getAllDepartment();


    //诊断
    @GetMapping("diagnosisType/getAllDiagnosisType")
    public Response getAllDiagnosisType();

    @PostMapping("diagnosisType/addDiagnosisType")
    public Response addDiagnosisType(@RequestParam Integer prescriptionId, @RequestParam String diagnosisType);


    //员工
    @GetMapping("employee/getAllEmployee/{id}")
    public Response getAllEmployee(@PathVariable Integer id);


    //医嘱
    @GetMapping("medicalAdvice/getAllMedicalAdvice")
    public Response getAllMedicalAdvice();

    @PostMapping("medicalAdvice/addMedicalAdvice")
    public Response addMedicalAdvice(@RequestParam Integer prescriptionId, @RequestParam String medicalAdvice);


    //患者
    @GetMapping("patient/getPatientById/{id}")
    public Response getPatientById(@PathVariable Integer id);

    @GetMapping("patient/getAllPatient")
    public Response getAllPatient();


    //处方
    @GetMapping("prescription/getAllPrescriptionType")
    public Response getAllPrescriptionType();


    //模板
    @PostMapping("template/getAllTemplate")
    public Response getAllTemplate(@RequestParam Integer pageNo,@RequestParam Integer pageSize, @RequestParam Integer prescriptionTypeId, @RequestParam Integer templatePermission, @RequestParam String templateNoOrName);

    @GetMapping("template/{id}")
    public Response getTemplateById(@PathVariable Integer id);
}
