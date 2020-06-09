package cn.project.action;

import cn.project.entity.vo.MedicineVO;
import cn.project.service.MedicineService;
import cn.project.service.MedicineServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

import java.util.HashMap;
import java.util.Map;

public class MedicineAction extends ActionSupport implements RequestAware {
    private static final long serialVersionUID = 1L;
    private Map<String,Object> request;
    private MedicineService medicineService;
    private MedicineVO medicineVO;
    private int id;
    private int n;

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    public MedicineVO getMedicineVO() {
        return medicineVO;
    }

    public void setMedicineVO(MedicineVO medicineVO) {
        this.medicineVO = medicineVO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMedicineService(MedicineServiceImpl medicineService) {
        this.medicineService = medicineService;
    }

    //获取所有药品信息
    public String list(){
        if(medicineVO.getPageNo() == null){
            medicineVO.setPageNo(1);
        }
        int pageSize = 5;
        Map<String,Object> map = new HashMap<String, Object>(){{
            put("pageNo",medicineVO.getPageNo());
            put("pageSize",pageSize);
            put("prescriptionTypeId",medicineVO.getPrescriptionTypeId());
            put("medicineStatus",medicineVO.getMedicineStatus());
            put("name",medicineVO.getName());
            put("startCreateTime",medicineVO.getStartCreateTime());
            put("endCreateTime",medicineVO.getEndCreateTime());
        }};
        request.put("page",medicineService.getAllMedicineByMap(map));
        request.put("prescriptionTypeId",medicineVO.getPrescriptionTypeId());
        request.put("medicineStatus",medicineVO.getMedicineStatus());
        request.put("name",medicineVO.getName());
        request.put("startCreateTime",medicineVO.getStartCreateTime());
        request.put("endCreateTime",medicineVO.getEndCreateTime());
        return SUCCESS;
    }


    //药品停用
    public String updateStatus(){
        n = medicineService.updateStatus(id);
        return SUCCESS;
    }



}
