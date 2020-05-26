package cn.project.service.diagnosisTypeService;

import cn.project.entity.DiagnosisType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiagnosisTypeService {
    List<DiagnosisType> getAllDiagnosisType();
    int addDiagnosisType(@Param("prescriptionId") Integer prescriptionId, @Param("diagnosisTypeId") Integer diagnosisTypeId);
}
