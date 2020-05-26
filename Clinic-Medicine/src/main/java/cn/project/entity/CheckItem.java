package cn.project.entity;


import java.io.Serializable;

public class CheckItem implements Serializable {
    private Integer id;
    private String itemNo;
    private String itemName;
    private String pinYinCode;
    private Long costPrice;
    private Long retailPrice;
    private Integer itemTypeId;
    private String itemTypeName;
    private String part;
    private Integer memberDiscount;
    private Integer itemStatus;
    private String remarks;
    private Integer prescriptionTypeId;
    private String prescriptionTypeName;
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPinYinCode() {
        return pinYinCode;
    }

    public void setPinYinCode(String pinYinCode) {
        this.pinYinCode = pinYinCode;
    }

    public Long getCostPrice() {
        return costPrice/100;
    }

    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

    public Long getRetailPrice() {
        return retailPrice/100;
    }

    public void setRetailPrice(Long retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public Integer getMemberDiscount() {
        return memberDiscount;
    }

    public void setMemberDiscount(Integer memberDiscount) {
        this.memberDiscount = memberDiscount;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getPrescriptionTypeId() {
        return prescriptionTypeId;
    }

    public void setPrescriptionTypeId(Integer prescriptionTypeId) {
        this.prescriptionTypeId = prescriptionTypeId;
    }

    public String getPrescriptionTypeName() {
        return prescriptionTypeName;
    }

    public void setPrescriptionTypeName(String prescriptionTypeName) {
        this.prescriptionTypeName = prescriptionTypeName;
    }
}
