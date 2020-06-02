package cn.project.service;

import cn.project.entity.PrescriptionMedicineX;
import cn.project.mapper.PrescriptionMedicineXMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionMedicineXServiceImpl extends ServiceImpl<PrescriptionMedicineXMapper, PrescriptionMedicineX> implements PrescriptionMedicineXService{
    @Override
    public int addPrescription_PrescriptionMedicineX(Integer prescriptionId, Integer prescriptionMedicineXId) {
        return this.baseMapper.addPrescription_PrescriptionMedicineX(prescriptionId, prescriptionMedicineXId);
    }
}
