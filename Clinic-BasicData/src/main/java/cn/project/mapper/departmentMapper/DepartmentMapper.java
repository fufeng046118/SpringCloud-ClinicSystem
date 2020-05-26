package cn.project.mapper.departmentMapper;

import cn.project.entity.Department;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "c2")
public interface DepartmentMapper {
    @Cacheable(key = "targetClass+':'+methodName")
    List<Department> getAllDepartment();
}
