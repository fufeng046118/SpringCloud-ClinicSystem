package cn.project.service.patientService;

import cn.project.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient getPatientById(Integer id);
    List<Patient> getAllPatient();
}
