package cn.project.service;

import cn.project.dao.MedicineDao;
import cn.project.entity.Medicine;

import java.util.List;
import java.util.Map;

public class MedicineService {
    private MedicineDao medicineDao;

    public void setMedicineDao(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    public List<Medicine> getAllMedicineByMap(Map<String,Object> map){
        return medicineDao.getAllMedicineByMap(map);
    }

}
