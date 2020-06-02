package cn.project.service;

import cn.project.entity.Prescription;
import cn.project.entity.PrescriptionMedicineC;
import cn.project.entity.PrescriptionMedicineX;
import cn.project.entity.PrescriptionMedicineZ;
import cn.project.mapper.PrescriptionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper,Prescription> implements PrescriptionService{

}
