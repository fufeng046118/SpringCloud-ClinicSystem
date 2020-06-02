package cn.project.service;

import cn.project.entity.PrescriptionMedicineX;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

public interface PrescriptionMedicineXService extends IService<PrescriptionMedicineX> {
    int addPrescription_PrescriptionMedicineX(@Param("prescriptionId") Integer prescriptionId, @Param("prescriptionMedicineXId") Integer prescriptionMedicineXId);

}
