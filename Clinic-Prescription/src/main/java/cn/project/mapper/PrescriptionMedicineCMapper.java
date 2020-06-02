package cn.project.mapper;

import cn.project.entity.PrescriptionMedicineC;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionMedicineCMapper extends BaseMapper<PrescriptionMedicineC> {
    int addPrescription_PrescriptionMedicineC(@Param("prescriptionId") Integer prescriptionId, @Param("prescriptionMedicineCId") Integer prescriptionMedicineXId);

}
