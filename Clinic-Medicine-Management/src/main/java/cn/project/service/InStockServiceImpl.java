package cn.project.service;

import cn.project.dao.InStockDao;
import cn.project.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class InStockServiceImpl implements InStockService {
    private InStockDao inStockDao;

    public void setInStockDao(InStockDao inStockDao) {
        this.inStockDao = inStockDao;
    }

    @Override
    public Page<InStock> getAll(Map<String,Object> map) {
        Page<InStock> page = new Page<>();
        List<InStock> inStockList = new ArrayList<>();
        page.setPageSize((int)map.get("pageSize"));
        page.setPageNo((int)map.get("pageNo"));
        int totalCount = inStockDao.count(map);
        if(totalCount > 0){
            page.setTotalCount(totalCount);
            if(page.getPageNo() < 1){
                page.setPageNo(1);
            }
            if(page.getPageNo() > page.getTotalPageCount()){
                page.setPageNo(page.getTotalPageCount());
            }
            map.put("pageNo",(page.getPageNo()-1)*page.getPageSize());
            map.put("pageSize",page.getPageSize());
            System.out.println("totalPageCount:"+page.getTotalPageCount());
            inStockList = inStockDao.getAll(map);
        }
        page.setList(inStockList);
        return page;
    }

    @Override
    public List<InStockType> getInStockType() {
        return inStockDao.getInStockType();
    }

    @Override
    public InStock getInStockById(int id) {
        return inStockDao.getInStockById(id);
    }

    @Override
    public List<InStockMedicine> getAllInStockMedicineById(int id) {
        return inStockDao.getAllInStockMedicineById(id);
    }

    @Override
    public int deleteInStockById(int id) {
        inStockDao.deleteInStockMedicineById(id);
        return inStockDao.deleteInStockById(id);
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return inStockDao.getAllManufacturer();
    }

    @Override
    public List<Employee> getAllEmployee() {
        return inStockDao.getAllEmployee();
    }

    @Override
    public int addInStock(InStock inStock) {
        return inStockDao.addInStock(inStock);
    }

    @Override
    public int addInStockMedicine(InStockMedicine inStockMedicine) {
        return inStockDao.addInStockMedicine(inStockMedicine);
    }

    @Override
    public int updateStatus(int inStockId, int statusId, Date date, int auditId) {
        return inStockDao.updateStatus(inStockId, statusId,date,auditId);
    }

    @Override
    public int updateMedicineStock(int id, Long count) {
        return inStockDao.updateMedicineStock(id,count);
    }

    @Override
    public int reInStock(int inStockId) {
        List<InStockMedicine> medicineList = inStockDao.getAllInStockMedicineById(inStockId);
        medicineList.forEach(inStockMedicine -> {
            inStockDao.updateMedicineStock(inStockMedicine.getMedicineId(),inStockMedicine.getCount());
        });
        return 1;
    }
}
