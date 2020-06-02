package cn.project.service;

import cn.project.entity.PrescriptionMedicineZ;
import cn.project.mapper.PrescriptionMedicineZMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionMedicineZServiceImpl extends ServiceImpl<PrescriptionMedicineZMapper, PrescriptionMedicineZ> implements PrescriptionMedicineZService{
    @Override
    public int addPrescription_PrescriptionMedicineZ(Integer prescriptionId, Integer prescriptionMedicineXId) {
        return this.baseMapper.addPrescription_PrescriptionMedicineZ(prescriptionId, prescriptionMedicineXId);
    }
}
