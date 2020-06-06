package cn.project.action;

import cn.project.service.MedicineService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

import java.util.HashMap;
import java.util.Map;

public class MedicineAction extends ActionSupport implements RequestAware {
    private static final long serialVersionUID = 1L;
    private Map<String,Object> request;
    private MedicineService medicineService;

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }


    public void setMedicineService(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public String list(){
        Map<String,Object> map = new HashMap<String, Object>(){{

        }};
        request.put("medicineList",medicineService.getAllMedicineByMap(null));
        return "list";
    }



}
