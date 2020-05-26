package cn.project.mapper.employeeMapper;

import cn.project.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> getAllEmployee(@Param("id") Integer id);
}
