package cn.project.action;

import cn.project.entity.InStock;
import cn.project.entity.InStockMedicine;
import cn.project.entity.vo.InStockVO;
import cn.project.service.InStockService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InStockAction extends ActionSupport implements RequestAware {
    private static final long serialVersionUID = 1L;
    private Map<String,Object> request;
    private InStockService inStockService;
    private InStockVO inStockVO;

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
        List<InStockMedicine> allInStockMedicineById = inStockService.getAllInStockMedicineById(inStockVO.getInStockId());
        InStock inStockById = inStockService.getInStockById(inStockVO.getInStockId());
        allInStockMedicineById.forEach(System.out::println);
        System.out.println(inStockById);
        return SUCCESS;
    }

}
