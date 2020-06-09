package cn.project.service;

import cn.project.entity.Medicine;
import cn.project.entity.Page;

import java.util.Map;

public interface MedicineService {
    Page<Medicine> getAllMedicineByMap(Map<String,Object> map);
    int updateStatus(int id);
}
