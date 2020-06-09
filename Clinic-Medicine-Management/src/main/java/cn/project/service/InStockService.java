package cn.project.service;

import cn.project.entity.InStock;
import cn.project.entity.InStockMedicine;
import cn.project.entity.InStockType;
import cn.project.entity.Page;

import java.util.List;
import java.util.Map;

public interface InStockService {
    Page<InStock> getAll(Map<String,Object> map);
    List<InStockType> getInStockType();
    InStock getInStockById(int id);
    List<InStockMedicine> getAllInStockMedicineById(int id);
}
