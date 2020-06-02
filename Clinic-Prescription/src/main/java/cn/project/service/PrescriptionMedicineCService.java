package cn.project.service;

import cn.project.entity.PrescriptionMedicineC;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

public interface PrescriptionMedicineCService extends IService<PrescriptionMedicineC> {
    int addPrescription_PrescriptionMedicineC(@Param("prescriptionId") Integer prescriptionId, @Param("prescriptionMedicineCId") Integer prescriptionMedicineXId);
}
