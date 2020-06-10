package cn.project.service;

import cn.project.dao.MedicineDaoImpl;
import cn.project.entity.Medicine;
import cn.project.entity.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MedicineServiceImpl implements MedicineService{
    private MedicineDaoImpl medicineDao;

    public void setMedicineDao(MedicineDaoImpl medicineDao) {
        this.medicineDao = medicineDao;
    }

    @Override
    public List<Medicine> getAllMedicineByMap(Map<String,Object> map){
        return medicineDao.getAllMedicineByMap(map);
    }
}
