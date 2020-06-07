package cn.project.action;

import cn.project.entity.Medicine;
import cn.project.entity.Page;
import cn.project.service.MedicineService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

import java.util.HashMap;
import java.util.Map;

public class MedicineAction extends ActionSupport implements RequestAware {
    private static final long serialVersionUID = 1L;
    private Map<String,Object> request;
    private MedicineService medicineService;
    private Integer pageNo;

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


    public void setMedicineService(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public String list(){
        if(pageNo == null){
            pageNo = 1;
        }
        int pageSize = 2;
        Map<String,Object> map = new HashMap<String, Object>(){{
            put("pageNo",pageNo);
            put("pageSize",pageSize);
            put("prescriptionTypeId",1);
            put("medicineStatus",1);
            put("name","注射液");
        }};
        request.put("page",medicineService.getAllMedicineByMap(map));
        return "list";
    }



}
