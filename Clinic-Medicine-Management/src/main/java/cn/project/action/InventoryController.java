package cn.project.action;

import cn.project.entity.vo.InventoryMedicineVo;
import cn.project.entity.vo.InventoryVo;
import cn.project.entity.vo.MedicineVO2;
import cn.project.service.InventoryService;
import com.opensymphony.xwork2.ActionContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class InventoryController {
    @Resource
    InventoryService inventoryService;
    private InventoryVo inventoryVo;
    private MedicineVO2 medicineVo;
    private Integer pageNo;
    private Integer updateId;
    private InventoryMedicineVo inventoryMedicineVo;

    public InventoryMedicineVo getInventoryMedicineVo() {
        return inventoryMedicineVo;
    }

    public void setInventoryMedicineVo(InventoryMedicineVo inventoryMedicineVo) {
        this.inventoryMedicineVo = inventoryMedicineVo;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public MedicineVO2 getMedicineVo() {
        return medicineVo;
    }

    public void setMedicineVo(MedicineVO2 medicineVo) {
        this.medicineVo = medicineVo;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public InventoryVo getInventoryVo() {
        return inventoryVo;
    }

    public void setInventoryVo(InventoryVo inventoryVo) {
        this.inventoryVo = inventoryVo;
    }
    public String findInventory(){
        if(pageNo==null||"".equals(pageNo)||pageNo<=0){
            this.pageNo=1;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("inventorydate1",inventoryVo.getInventorydate1());
        map.put("inventorydate2",inventoryVo.getInventorydate2());
        map.put("inventoryno",inventoryVo.getInventoryno());
        int total=inventoryService.findCount(map);
        int pageCount=total%3==0?total/3:total/3+1;
        if(pageNo>pageCount)pageNo=pageCount;
        map.put("pageNo",this.pageNo);
        map.put("pageSize",3);
        ActionContext.getContext().put("inventoryList",inventoryService.findInventory(map));
        ActionContext.getContext().put("pageNo",pageNo);
        ActionContext.getContext().put("pageCount",pageCount);
        ActionContext.getContext().put("inventoryVo",this.inventoryVo);
        return "findInventory";
    }

        public String toInventoryDetailByInfo(){  //去查看页面
            if(pageNo==null||"".equals(pageNo)||pageNo<=0){
                this.pageNo=1;
            }
            Map<String,Object>map = new HashMap<>();
            map.put("prescriptionid",medicineVo.getPrescriptionId());
            map.put("mno",medicineVo.getMno());
            map.put("id",inventoryVo.getGeo());

            int total=inventoryService.findInventoryMedicineCount(map);
            int pageCount=total%3==0?total/3:total/3+1;
            if(pageNo>pageCount)pageNo=pageCount;
            map.put("pageNo",this.pageNo);
            map.put("pageSize",3);
            ActionContext.getContext().put("inventory",inventoryService.findInventoryDetail(inventoryVo.getGeo()));   //头部盘点信息
            ActionContext.getContext().put("inventoryMedicine",inventoryService.findInventoryMedicineByInfo(map));
            ActionContext.getContext().put("pageNo",pageNo);
            ActionContext.getContext().put("pageCount",pageCount);
            ActionContext.getContext().put("medicineVo",medicineVo);
            if(inventoryVo.getStatus()==1){
                return "toInventoryDetailByInfo";
            }else{
                return "toUpdateInventoryDetail";
            }

        }
        public String updateInventoryDetail(){
            if("".equals(inventoryMedicineVo.getDifference())){
                inventoryMedicineVo.setDifference(0);
            }
            if ("".equals(inventoryMedicineVo.getInventorystock())){
                inventoryMedicineVo.setInventorystock(0);
            }
            if("".equals(inventoryMedicineVo.getMark())){
                inventoryMedicineVo.setMark(null);
            }
            updateId=inventoryService.updateInventoryDetail(inventoryMedicineVo,inventoryVo.getGeo());
            return "updateInventoryDetail";
        }
        public String delInventory(){
        inventoryService.delInventory(inventoryVo.getGeo());
        return "delInventory";
        }
}
