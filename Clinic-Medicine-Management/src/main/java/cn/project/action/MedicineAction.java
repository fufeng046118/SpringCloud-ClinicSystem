package cn.project.action;

import cn.project.entity.Medicine;
import cn.project.entity.vo.MedicineVO1;
import cn.project.service.MedicineService;
import cn.project.service.MedicineServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicineAction extends ActionSupport{
    private static final long serialVersionUID = 1L;
    private MedicineService medicineService;
    private List<Medicine> medicineList;
    private MedicineVO1 medicineVO;

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public MedicineVO1 getMedicineVO() {
        return medicineVO;
    }

    public void setMedicineVO(MedicineVO1 medicineVO) {
        this.medicineVO = medicineVO;
    }


    public void setMedicineService(MedicineServiceImpl medicineService) {
        this.medicineService = medicineService;
    }

    //获取所有药品信息
    public String list(){
        Map<String,Object> map = new HashMap<String, Object>(){{
            put("prescriptionTypeId",medicineVO.getPrescriptionTypeId());
            put("name",medicineVO.getName());
        }};
        medicineList = medicineService.getAllMedicineByMap(map);
        return Action.SUCCESS;
    }


}
