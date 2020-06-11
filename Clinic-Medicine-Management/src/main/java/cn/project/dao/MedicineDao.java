package cn.project.dao;

import cn.project.entity.Medicine;

import java.util.List;
import java.util.Map;

public interface MedicineDao {
    List<Medicine> getAllMedicineByMap(Map<String,Object> map);

}
