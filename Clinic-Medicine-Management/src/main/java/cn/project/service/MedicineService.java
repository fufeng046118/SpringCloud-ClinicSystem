package cn.project.service;

import cn.project.dao.MedicineDao;
import cn.project.entity.Medicine;
import cn.project.entity.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MedicineService {
    private MedicineDao medicineDao;

    public void setMedicineDao(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    public Page<Medicine> getAllMedicineByMap(Map<String,Object> map){
        Page<Medicine> page = new Page<>();
        List<Medicine> medicineList = new ArrayList<>();
        page.setPageSize((int)map.get("pageSize"));
        page.setPageNo((int)map.get("pageNo"));
        int totalCount = medicineDao.totalCount(map);
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
            medicineList = medicineDao.getAllMedicineByMap(map);
        }
        page.setList(medicineList);
        return page;
    }

}
