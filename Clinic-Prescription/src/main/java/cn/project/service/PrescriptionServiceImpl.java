package cn.project.service;

import cn.project.entity.Prescription;
import cn.project.entity.PrescriptionMedicineC;
import cn.project.entity.PrescriptionMedicineX;
import cn.project.entity.PrescriptionMedicineZ;
import cn.project.mapper.PrescriptionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Resource
    PrescriptionMapper prescriptionMapper;
    @Override
    public int addPrescription(Prescription prescription) {
        return prescriptionMapper.addPrescription(prescription);
    }

    @Override
    public int addPrescriptionMedicineX(PrescriptionMedicineX prescriptionMedicineX) {
        return prescriptionMapper.addPrescriptionMedicineX(prescriptionMedicineX);
    }

    @Override
    public int addPrescriptionMedicineZ(PrescriptionMedicineZ prescriptionMedicineZ) {
        return prescriptionMapper.addPrescriptionMedicineZ(prescriptionMedicineZ);
    }

    @Override
    public int addPrescriptionMedicineC(PrescriptionMedicineC prescriptionMedicineC) {
        return prescriptionMapper.addPrescriptionMedicineC(prescriptionMedicineC);
    }

    @Override
    public int addPrescription_PrescriptionMedicineZ(Integer prescriptionId, Integer prescriptionMedicineZId) {
        return prescriptionMapper.addPrescription_PrescriptionMedicineZ(prescriptionId,prescriptionMedicineZId);
    }

    @Override
    public int addPrescription_PrescriptionMedicineC(Integer prescriptionId, Integer prescriptionMedicineCId) {
        return prescriptionMapper.addPrescription_PrescriptionMedicineC(prescriptionId,prescriptionMedicineCId);
    }

    @Override
    public int addPrescription_PrescriptionMedicineX(Integer prescriptionId, Integer prescriptionMedicineXId) {
        return prescriptionMapper.addPrescription_PrescriptionMedicineX(prescriptionId,prescriptionMedicineXId);
    }
}
