package cn.project.mapper;

import cn.project.entity.PrescriptionMedicineX;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionMedicineXMapper extends BaseMapper<PrescriptionMedicineX> {
    int addPrescription_PrescriptionMedicineX(@Param("prescriptionId") Integer prescriptionId, @Param("prescriptionMedicineXId") Integer prescriptionMedicineXId);
    List<PrescriptionMedicineX> getAllPrescriptionMedicineXById(int id);
}
