package cn.project.service;

import cn.project.entity.PrescriptionMedicineC;
import cn.project.mapper.PrescriptionMedicineCMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionMedicineCServiceImpl extends ServiceImpl<PrescriptionMedicineCMapper, PrescriptionMedicineC> implements PrescriptionMedicineCService{
    @Override
    public int addPrescription_PrescriptionMedicineC(Integer prescriptionId, Integer prescriptionMedicineXId) {
        return this.baseMapper.addPrescription_PrescriptionMedicineC(prescriptionId, prescriptionMedicineXId);
    }

    @Override
    public List<PrescriptionMedicineC> getAllPrescriptionMedicineCById(int id) {
        return this.baseMapper.getAllPrescriptionMedicineCById(id);
    }
}
