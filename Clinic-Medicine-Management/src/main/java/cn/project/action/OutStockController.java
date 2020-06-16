package cn.project.action;

import cn.project.entity.Medicine;
import cn.project.entity.Outstock;
import cn.project.entity.OutstockMedicine;
import cn.project.entity.vo.MedicineVO2;
import cn.project.entity.vo.OutstockVo;
import cn.project.service.OutStockService;
import com.opensymphony.xwork2.ActionContext;

import javax.annotation.Resource;
import java.util.*;

public class OutStockController {
    @Resource
    OutStockService outStockService;
    private OutstockVo outstockVo;
    private List<Medicine> medicines = new ArrayList<>();
    private MedicineVO2 medicineVo;
    private OutstockMedicine outstockMedicine;
    private Outstock outstock;
    private long outstockid;
    private long updateStatusId;

    public long getUpdateStatusId() {
        return updateStatusId;
    }

    public void setUpdateStatusId(long updateStatusId) {
        this.updateStatusId = updateStatusId;
    }

    public long getOutstockid() {
        return outstockid;
    }

    public void setOutstockid(long outstockid) {
        this.outstockid = outstockid;
    }

    public Outstock getOutstock() {
        return outstock;
    }

    public void setOutstock(Outstock outstock) {
        this.outstock = outstock;
    }

    public OutstockMedicine getOutstockMedicine() {
        return outstockMedicine;
    }

    public void setOutstockMedicine(OutstockMedicine outstockMedicine) {
        this.outstockMedicine = outstockMedicine;
    }

    public MedicineVO2 getMedicineVo() {
        return medicineVo;
    }

    public void setMedicineVo(MedicineVO2 medicineVo) {
        this.medicineVo = medicineVo;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public OutstockVo getOutstockVo() {
        return outstockVo;
    }

    public void setOutstockVo(OutstockVo outstockVo) {
        this.outstockVo = outstockVo;
    }

    private Integer pageNo;


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo==null||"".equals(pageNo)||pageNo<=0){
            this.pageNo=1;
        }else{
            this.pageNo = pageNo;
        }
    }
    public String findOutStock(){
        if(pageNo==null||"".equals(pageNo)||pageNo<=0){
            this.pageNo=1;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("typeid",this.outstockVo.getTypeId());
        map.put("statusid",this.outstockVo.getStatusId());
        map.put("outstockno",this.outstockVo.getOutstockno());
        int total=outStockService.findCount(map);
        int pageCount=total%3==0?total/3:total/3+1;
        if(pageNo>pageCount)pageNo=pageCount;
        map.put("pageNo",this.pageNo);
        map.put("pageSize",3);
        List<Outstock> list = outStockService.findOutStock(map);

        ActionContext.getContext().put("outStockList",list);
        ActionContext.getContext().put("pageNo",pageNo);
        ActionContext.getContext().put("pageCount",pageCount);
        ActionContext.getContext().put("typeList",outStockService.findType());
        ActionContext.getContext().put("outstockVo",this.outstockVo);
        return "outStock";
    }
    public String findOutStockDetail(){
        List<OutstockMedicine> list = outStockService.findOutstockMedicine(this.outstockVo.getGeo());
        ActionContext.getContext().put("outstockdetail",outStockService.findOutStockDetail(this.outstockVo.getGeo()));
        ActionContext.getContext().put("outstockmedicine",list);
        return "outStockDetail";
    }
    public String delOutStock(){
        outStockService.delOutstockMedicine(this.outstockVo.getGeo());
        outStockService.delOutstock(this.outstockVo.getGeo());
        return "delTooutStock";
    }
    public String addAgainOutStock(){  //再次出库  首先根据出库表ID查询出库药品信息表中数据 （包含药品信息）  对库存修改
        List<OutstockMedicine> list = outStockService.findOutstockMedicine(outstockVo.getGeo());
        for (OutstockMedicine o :list) {
            outStockService.updateStock(o.getCount(),o.getMedicineid());
            //outStockService.updateOutStockMedicine(o.getId());
        }
        return "addAgainOutStock";
    }
    public String toaddOutStock(){
        ActionContext.getContext().put("employeeList",outStockService.findEmployee());
        ActionContext.getContext().put("typeList",outStockService.findType());
        ActionContext.getContext().put("medicines",outStockService.findMedicine());
        return "toaddOutStock";
    }
    public String findMedicine(){
        Map<String,Object> map = new HashMap<>();
        map.put("prescriptionid",medicineVo.getPrescriptionId());
        map.put("mno",medicineVo.getMno());
        medicines=outStockService.findMedicineByInfo(map);
        return "findMedicine";
    }
    public String addOutstockMedicine(){
        //System.out.println("getMedicineid="+outstockMedicine.getMedicineid());
        outStockService.addOutstockMedicine(outstockMedicine);
        outstockid=outstock.getId();
        return "addOutstockMedicine";
    }
    public String addOutstock(){
        System.out.println("date="+outstock.getOutdate());
        //System.out.println("getMedicineid="+outstock.getOutstockno());
        outStockService.addOutstock(outstock);
        outstockid=outstock.getId();
        return "comeid";
    }
    public String toUpdateOutStock(){
        List<OutstockMedicine> list = outStockService.findOutstockMedicine(this.outstockVo.getGeo());
        ActionContext.getContext().put("employeeList",outStockService.findEmployee());
        ActionContext.getContext().put("typeList",outStockService.findType());
        ActionContext.getContext().put("outstockdetail",outStockService.findOutStockDetail(this.outstockVo.getGeo()));
        ActionContext.getContext().put("medicines",outStockService.findMedicine());
        ActionContext.getContext().put("outstockmedicine",list);
        return "toUpdateOutStock";
    }
    public String updateOutStock1(){  //1编辑时首先删除原来表数据  然后插入编辑后的出库信息  再异步执行2  新增出库药品详情表
        outStockService.delOutstockMedicine(this.outstockVo.getGeo());
        outStockService.delOutstock(this.outstockVo.getGeo());
        outStockService.addOutstock(outstock);
        outstockid=outstock.getId();
        return "updateOutStock1";
    }
    public String updateOutStock2(){  //2编辑时新增出库药品详情表
        outStockService.addOutstockMedicine(outstockMedicine);
        if (this.updateStatusId==1){
            outStockService.updateStock(outstockMedicine.getCount(),outstockMedicine.getMedicineid());
        }
        outstockid=outstock.getId();
        return "updateOutStock2";
    }
}
