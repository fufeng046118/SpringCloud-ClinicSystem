package cn.project.mapper.additionalFees;

import cn.project.entity.AdditionalFees;
import cn.project.entity.Prescription_AdditionalFees;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface AdditionalFeesMapper {
    List<AdditionalFees> getAllAdditionalFees();
    int addAdditionalFees(@Param("p") Prescription_AdditionalFees prescription_additionalFees);
}
