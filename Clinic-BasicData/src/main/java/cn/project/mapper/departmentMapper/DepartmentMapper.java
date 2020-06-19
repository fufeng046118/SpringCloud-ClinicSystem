package cn.project.mapper.departmentMapper;

import cn.project.entity.Department;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface DepartmentMapper {
    List<Department> getAllDepartment();
}
