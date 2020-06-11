package cn.project.dao;

import cn.project.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
public interface InStockDao {
    List<InStock> getAll(Map<String,Object> map);
    int count(Map<String,Object> map);
    List<InStockType> getInStockType();
    InStock getInStockById(int id);
    List<InStockMedicine> getAllInStockMedicineById(int id);
    int deleteInStockById(int id);
    int deleteInStockMedicineById(int id);
    List<Manufacturer> getAllManufacturer();
    List<Employee> getAllEmployee();
    int addInStock(InStock inStock);
    int addInStockMedicine(InStockMedicine inStockMedicine);
    int updateStatus(int inStockId, int statusId, Date date, int auditId);
    int updateMedicineStock(int id,Long count);
}
