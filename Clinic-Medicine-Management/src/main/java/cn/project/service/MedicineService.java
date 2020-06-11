package cn.project.service;

import cn.project.entity.Medicine;
import cn.project.entity.Page;

import java.util.List;
import java.util.Map;

public interface MedicineService {
    List<Medicine> getAllMedicineByMap(Map<String,Object> map);

}
