package cn.project.dao;

import cn.project.entity.InStock;
import cn.project.entity.InStockMedicine;
import cn.project.entity.InStockType;

import java.util.List;
import java.util.Map;

public interface InStockDao {
    List<InStock> getAll(Map<String,Object> map);
    int count(Map<String,Object> map);
    List<InStockType> getInStockType();
    InStock getInStockById(int id);
    List<InStockMedicine> getAllInStockMedicineById(int id);
}
