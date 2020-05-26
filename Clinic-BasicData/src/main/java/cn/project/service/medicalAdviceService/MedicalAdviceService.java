package cn.project.service.medicalAdviceService;

import cn.project.entity.MedicalAdvice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MedicalAdviceService {
    List<MedicalAdvice> getAllMedicalAdvice();
    int addMedicalAdvice(@Param("prescriptionId") Integer prescriptionId, @Param("medicalAdviceId") Integer medicalAdviceId);
}
