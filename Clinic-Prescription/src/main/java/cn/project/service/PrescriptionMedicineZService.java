package cn.project.service;

import cn.project.entity.PrescriptionMedicineZ;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PrescriptionMedicineZService extends IService<PrescriptionMedicineZ> {
    int addPrescription_PrescriptionMedicineZ(@Param("prescriptionId") Integer prescriptionId, @Param("prescriptionMedicineZId") Integer prescriptionMedicineXId);
    List<PrescriptionMedicineZ> getAllPrescriptionMedicineZById(int id);

}
