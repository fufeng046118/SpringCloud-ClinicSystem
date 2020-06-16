package cn.project.mapper;

import cn.project.entity.PrescriptionMedicineZ;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionMedicineZMapper extends BaseMapper<PrescriptionMedicineZ> {
    int addPrescription_PrescriptionMedicineZ(@Param("prescriptionId") Integer prescriptionId, @Param("prescriptionMedicineZId") Integer prescriptionMedicineXId);
    List<PrescriptionMedicineZ> getAllPrescriptionMedicineZById(int id);

}
