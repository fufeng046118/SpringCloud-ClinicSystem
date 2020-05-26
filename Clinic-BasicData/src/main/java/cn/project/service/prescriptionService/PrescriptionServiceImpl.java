package cn.project.service.prescriptionService;

import cn.project.entity.PrescriptionType;
import cn.project.mapper.prescriptionMapper.PrescriptionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Resource
    PrescriptionMapper prescriptionMapper;
    @Override
    public List<PrescriptionType> getAllPrescriptionType() {
        return prescriptionMapper.getAllPrescriptionType();
    }
}
