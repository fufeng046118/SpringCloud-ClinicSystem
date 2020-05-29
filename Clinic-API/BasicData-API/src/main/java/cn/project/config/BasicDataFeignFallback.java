package cn.project.config;

import cn.project.controller.BasicDataFeign;
import cn.project.entity.Prescription_AdditionalFees;
import cn.project.utils.Response;
import cn.project.utils.ResponseEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class BasicDataFeignFallback implements BasicDataFeign {
    //附加费用
    @Override
    public Response getAllAdditionalFees() {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }

    @Override
    public Response addAdditionalFees(Prescription_AdditionalFees prescription_additionalFees) {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }


    //科室
    @Override
    public Response getAllDepartment() {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }


    //诊断
    @Override
    public Response getAllDiagnosisType() {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }

    @Override
    public Response addDiagnosisType(Integer prescriptionId, String diagnosisType) {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }


    //员工
    @Override
    public Response getAllEmployee(Integer id) {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }


    //医嘱
    @Override
    public Response getAllMedicalAdvice() {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }

    @Override
    public Response addMedicalAdvice(Integer prescriptionId, String medicalAdvice) {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }


    //患者
    @Override
    public Response getPatientById(Integer id) {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }

    @Override
    public Response getAllPatient() {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }


    //处方
    @Override
    public Response getAllPrescriptionType() {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }


    //模板
    @Override
    public Response getAllTemplate(Integer pageNo, Integer pageSize, Integer prescriptionTypeId, Integer templatePermission, String templateNoOrName) {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }

    @Override
    public Response getTemplateById(Integer id) {
        return new Response(ResponseEnum.SUCCESS).setResponseBody("服务器未能响应");
    }
}
