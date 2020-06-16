package cn.project.service;

import cn.project.entity.*;
import cn.project.mapper.MedicineMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "c1")
public class MedicineServiceImpl implements MedicineService {
    @Resource
    MedicineMapper medicineMapper;
    @Override
    @Cacheable(key = "targetClass+':'+methodName")
    public List<MedicineType> getAllMedicineType() {
        return medicineMapper.getAllMedicineType();
    }

    @Override
    @Cacheable(key = "targetClass+':'+methodName")
    public List<CheckItemType> getAllCheckItemType() {
        return medicineMapper.getAllCheckItemType();
    }

    @Override
    @Cacheable(key = "targetClass+':'+methodName+':'+ # prescriptionTypeId +':'+ # medicineTypeId +':'+ # nameOrPinYin +':'+ # pageNo +':'+ # pageSize")
    public PageInfo<Medicine> getAllMedicine(Integer prescriptionTypeId, Integer medicineTypeId, String nameOrPinYin, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Medicine> list = medicineMapper.getAllMedicine(prescriptionTypeId,medicineTypeId,nameOrPinYin,pageNo,pageSize);
        System.out.println(list);
        PageInfo<Medicine> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Cacheable(key = "targetClass+':'+methodName+':'+ # itemTypeId + # nameOrPinYin+':'+# nameOrPinYin +':'+ # pageNo +':'+ # pageSize")
    public PageInfo<CheckItem> getAllCheckItem(Integer itemTypeId, String nameOrPinYin, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        //这个pageNo,pageSize传过去只是用到缓存的key中并没有做查询
        List<CheckItem> list = medicineMapper.getAllCheckItem(itemTypeId,nameOrPinYin,pageNo,pageSize);
        PageInfo<CheckItem> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<MedicineUsage> getMedicineUsage() {
        return medicineMapper.getMedicineUsage();

    }

    @Override
    public int updateMedicine(int id, int stock) {
        System.out.println(id+","+stock);
        return medicineMapper.updateMedicine(id, stock);
    }
}
