package cn.project.service.additionalFeesService;

import cn.project.entity.AdditionalFees;
import cn.project.entity.Prescription_AdditionalFees;

import java.util.List;

public interface AdditionalFeesService {
    List<AdditionalFees> getAllAdditionalFees();
    int addAdditionalFees(Prescription_AdditionalFees prescription_additionalFees);
}
