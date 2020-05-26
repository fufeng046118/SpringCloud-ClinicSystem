package cn.project.mapper.patientMapper;

import cn.project.entity.Patient;

import java.util.List;

public interface PatientMapper {
    Patient getPatientById(Integer id);
    List<Patient> getAllPatient();
}
