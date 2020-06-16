package cn.project.service;

import cn.project.dao.OutStockDao;
import cn.project.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OutStockService {
    @Resource
    OutStockDao outStockDao;
    public List<Outstock> findOutStock(Map<String,Object> map){
        return outStockDao.findOutstock(map);
    }

    public int findCount(Map<String, Object> map) {
        return outStockDao.findCount(map);
    }
    public List<Outstocktype> findType(){
        return outStockDao.findType();
    }
    public Outstock findOutStockDetail(Integer id){
        return outStockDao.findOutStockDetail(id);
    }
    public void delOutstock(Integer id){
        outStockDao.delOutstock(id);
    }
    public void delOutstockMedicine(Integer id){
        outStockDao.delOutstockMedicine(id);
    }
    public List<OutstockMedicine> findOutstockMedicine(Integer id){
        return outStockDao.findOutstockMedicine(id);
    }
    public List<Medicine> findMedicine(){
        return outStockDao.findMedicine();
    }
    public List<Medicine> findMedicineByInfo(Map<String,Object> map){
        return outStockDao.findMedicineByInfo(map);
    }
    public void addOutstockMedicine(OutstockMedicine outstockMedicine){
        outStockDao.addOutstockMedicine(outstockMedicine);
    }
    public void addOutstock(Outstock outstock){
        outStockDao.addOutstock(outstock);
    }
    public void updateStock(long count,long id) {   //对药品库存进行修改
        outStockDao.updateStock(count,id);
    }
    public List<Employee> findEmployee(){
        return outStockDao.findEmployee();
    }
/*    public void updateOutStockMedicine(long id) {   //再次出库对出库详情表修改库存
        outStockDao.updateOutStockMedicine(id);
    }*/

}
