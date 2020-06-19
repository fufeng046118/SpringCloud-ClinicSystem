package cn.project.service.medicalAdviceService;

import cn.project.entity.MedicalAdvice;
import cn.project.mapper.medicalAdviceMapper.MedicalAdviceMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MedicalAdviceServiceImpl implements MedicalAdviceService {
    @Resource
    MedicalAdviceMapper medicalAdviceMapper;
    @Override
    public List<MedicalAdvice> getAllMedicalAdvice() {
        return medicalAdviceMapper.getAllMedicalAdvice();
    }

    @Override
    public int addMedicalAdvice(Integer prescriptionId, Integer medicalAdviceId) {
        return medicalAdviceMapper.addMedicalAdvice(prescriptionId,medicalAdviceId);
    }
}
