package cn.project.mapper;

import cn.project.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface MedicineMapper {
    List<MedicineType> getAllMedicineType();
    List<CheckItemType> getAllCheckItemType();
    List<Medicine> getAllMedicine(@Param("prescriptionTypeId") Integer prescriptionTypeId, @Param("medicineTypeId") Integer medicineTypeId, @Param("nameOrPinYin") String nameOrPinYin, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
    List<CheckItem> getAllCheckItem(@Param("itemTypeId") Integer itemTypeId, @Param("nameOrPinYin") String nameOrPinYin, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
    List<MedicineUsage> getMedicineUsage();
    int updateMedicine(@Param("id")int id,@Param("stock") int stock);
}
