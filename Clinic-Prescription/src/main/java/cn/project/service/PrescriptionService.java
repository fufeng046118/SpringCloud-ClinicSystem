package cn.project.service;

import cn.project.entity.Prescription;
import cn.project.entity.PrescriptionMedicineC;
import cn.project.entity.PrescriptionMedicineX;
import cn.project.entity.PrescriptionMedicineZ;
import org.apache.ibatis.annotations.Param;

public interface PrescriptionService {
    int addPrescription(Prescription prescription);
    int addPrescriptionMedicineX(PrescriptionMedicineX prescriptionMedicineX);
    int addPrescriptionMedicineZ(PrescriptionMedicineZ prescriptionMedicineZ);
    int addPrescriptionMedicineC(PrescriptionMedicineC prescriptionMedicineC);

    int addPrescription_PrescriptionMedicineZ(@Param("prescriptionId") Integer prescriptionId, @Param("prescriptionMedicineZId") Integer prescriptionMedicineZId);
    int addPrescription_PrescriptionMedicineC(@Param("prescriptionId") Integer prescriptionId, @Param("prescriptionMedicineCId") Integer prescriptionMedicineCId);
    int addPrescription_PrescriptionMedicineX(@Param("prescriptionId") Integer prescriptionId, @Param("prescriptionMedicineXId") Integer prescriptionMedicineXId);

}
