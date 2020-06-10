package cn.project.service;

import cn.project.entity.*;

import java.util.List;
import java.util.Map;

public interface InStockService {
    Page<InStock> getAll(Map<String,Object> map);
    List<InStockType> getInStockType();
    InStock getInStockById(int id);
    List<InStockMedicine> getAllInStockMedicineById(int id);
    int deleteInStockById(int id);
    List<Manufacturer> getAllManufacturer();
    List<Employee> getAllEmployee();
}
