package cn.project.action;

import cn.project.entity.InStock;
import cn.project.entity.InStockMedicine;
import cn.project.entity.vo.InStockVO;
import cn.project.service.InStockService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InStockAction extends ActionSupport implements RequestAware {
    private static final long serialVersionUID = 1L;
    private Map<String,Object> request;
    private InStockService inStockService;
    private InStockVO inStockVO;
    private InStock inStock;
    private InStockMedicine inStockMedicine;
    private Long num;
    private int status;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public InStockMedicine getInStockMedicine() {
        return inStockMedicine;
    }

    public void setInStockMedicine(InStockMedicine inStockMedicine) {
        this.inStockMedicine = inStockMedicine;
    }

    public InStock getInStock() {
        return inStock;
    }

    public void setInStock(InStock inStock) {
        this.inStock = inStock;
    }

    public void setInStockService(InStockService inStockService) {
        this.inStockService = inStockService;
    }

    public InStockVO getInStockVO() {
        return inStockVO;
    }

    public void setInStockVO(InStockVO inStockVO) {
        this.inStockVO = inStockVO;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }


    //获取所有入库信息
    public String getAllInStock(){
        if(inStockVO.getPageNo() == null){
            inStockVO.setPageNo(1);
        }
        int pageSize = 5;
        Map<String,Object> map = new HashMap<String, Object>(){{
            put("pageNo",inStockVO.getPageNo());
            put("pageSize",pageSize);
            put("name",inStockVO.getName());
            put("statusId",inStockVO.getStatusId());
            put("type",inStockVO.getType());
        }};
        request.put("page",inStockService.getAll(map));
        request.put("inStockTypeList",inStockService.getInStockType());
        request.put("name",inStockVO.getName());
        request.put("statusId",inStockVO.getStatusId());
        request.put("type",inStockVO.getType());
        return SUCCESS;
    }

    //查看入库详情
    public String showInStockInfoById(){
        List<InStockMedicine> inStockMedicineList = inStockService.getAllInStockMedicineById(inStockVO.getInStockId());
        InStock inStock = inStockService.getInStockById(inStockVO.getInStockId());
        request.put("inStock",inStock);
        request.put("inStockMedicineList",inStockMedicineList);
        return SUCCESS;
    }

    //删除入库药品
    public String deleteInStockById(){
        inStockService.deleteInStockById(inStockVO.getInStockId());
        return SUCCESS;
    }

    //去新增入库记录
    public String toAddInStock(){
        request.put("employeeList",inStockService.getAllEmployee());
        request.put("inStockTypeList",inStockService.getInStockType());
        request.put("manufacturerList",inStockService.getAllManufacturer());
        return SUCCESS;
    }

    //做新增入库
    public String doAddInStock(){
        inStock.setAuditDate(new Date());
        inStockService.addInStock(inStock);
        num = inStock.getId();
        return SUCCESS;
    }

    //新增入库的药品详细信息
    public String addInStockMedicine(){
        inStockService.addInStockMedicine(inStockMedicine);
        if(inStockMedicine.getId() > 0 && inStock.getStatusId() == 2){
            inStockService.updateMedicineStock(inStockMedicine.getMedicineId(),inStockMedicine.getCount());
        }
        return SUCCESS;
    }

    //修改时的删除
    public String delInStock(){
        inStockService.deleteInStockById(inStockVO.getInStockId());
        return SUCCESS;
    }

    //to编辑入库信息
    public String toUpdateStatus(){
        List<InStockMedicine> inStockMedicineList = inStockService.getAllInStockMedicineById(inStockVO.getInStockId());
        InStock inStock = inStockService.getInStockById(inStockVO.getInStockId());
        request.put("employeeList",inStockService.getAllEmployee());
        request.put("inStockTypeList",inStockService.getInStockType());
        request.put("manufacturerList",inStockService.getAllManufacturer());
        request.put("inStock",inStock);
        request.put("inStockMedicineList",inStockMedicineList);
        return SUCCESS;
    }

    //再次入库
    public String reInStock(){
        inStockService.reInStock(inStockVO.getInStockId());
        return SUCCESS;
    }


}
