package cn.project.service.patientService;

import cn.project.entity.Patient;
import cn.project.mapper.patientMapper.PatientMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Resource
    PatientMapper patientMapper;
    @Override
    public Patient getPatientById(Integer id) {
        return patientMapper.getPatientById(id);
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientMapper.getAllPatient();
    }
}
