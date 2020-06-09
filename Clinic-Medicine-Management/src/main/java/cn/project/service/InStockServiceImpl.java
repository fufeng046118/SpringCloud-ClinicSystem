package cn.project.service;

import cn.project.dao.InStockDao;
import cn.project.entity.*;

import java.util.ArrayList;
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
}
