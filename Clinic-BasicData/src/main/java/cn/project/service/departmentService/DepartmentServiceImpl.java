package cn.project.service.departmentService;

import cn.project.entity.Department;
import cn.project.mapper.departmentMapper.DepartmentMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "c1")
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    DepartmentMapper departmentMapper;
    @Override
    @Cacheable(key = "targetClass+':'+methodName")
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment();
    }
}
