package cn.project.service;

import cn.project.dao.InventoryDao;
import cn.project.entity.Inventory;
import cn.project.entity.InventoryMedicine;
import cn.project.entity.vo.InventoryMedicineVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {
    @Resource
    InventoryDao inventoryDao;
    public List<Inventory> findInventory(Map<String,Object> map){
        return inventoryDao.findInventory(map);
    }
    public int findCount(Map<String,Object> map){
        return inventoryDao.findCount(map);
    }
    public Inventory findInventoryDetail(Integer id){ //盘点查看
        return inventoryDao.findInventoryDetail(id);
    }
    public List<InventoryMedicine> findInventoryMedicineByInfo(Map<String,Object> map){
        return inventoryDao.findInventoryMedicineByInfo(map);
    }
    public int findInventoryMedicineCount(Map<String,Object> map){
        return inventoryDao.findInventoryMedicineCount(map);
    }
    public int updateInventoryDetail(InventoryMedicineVo inventoryMedicineVo, Integer inventoryid){
        inventoryDao.updateStatus(inventoryid);
        return inventoryDao.updateInventoryDetail(inventoryMedicineVo);
    }
    public void delInventory(Integer id){
        inventoryDao.delInventoryMedicine(id);
        inventoryDao.delInventory(id);
    }
}
