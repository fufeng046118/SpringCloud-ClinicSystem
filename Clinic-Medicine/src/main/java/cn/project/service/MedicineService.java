package cn.project.service;

import cn.project.entity.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MedicineService {
    List<MedicineType> getAllMedicineType();
    List<CheckItemType> getAllCheckItemType();
    PageInfo<Medicine> getAllMedicine(Integer prescriptionTypeId, Integer medicineTypeId, String nameOrPinYin, Integer pageNo, Integer pageSize);
    PageInfo<CheckItem> getAllCheckItem(Integer itemTypeId, String nameOrPinYin, Integer pageNo, Integer pageSize);
    List<MedicineUsage> getMedicineUsage();
    int updateMedicine(int id,int stock);
}
