package cn.project.entity.vo;


public class InStockVO {
    private String name;
    private Integer pageNo;
    private String statusId;
    private String type;
    private int inStockId;

    public int getInStockId() {
        return inStockId;
    }

    public void setInStockId(int inStockId) {
        this.inStockId = inStockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
