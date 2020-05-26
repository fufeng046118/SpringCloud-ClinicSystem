package cn.project.entity;


import java.io.Serializable;

//检查处方
public class PrescriptionMedicineC implements Serializable {
    private Integer id;
    private Integer checkItemId;
    private String checkItemName;
    private String part;
    private Integer quantity;
    private Integer itemTypeId;
    private Long amount;
    private String remarks;
    private Integer prescriptionTypeId;
    private String prescriptionTypeName;
    private Long unitPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCheckItemId() {
        return checkItemId;
    }

    public void setCheckItemId(Integer checkItemId) {
        this.checkItemId = checkItemId;
    }

    public String getCheckItemName() {
        return checkItemName;
    }

    public void setCheckItemName(String checkItemName) {
        this.checkItemName = checkItemName;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Long getAmount() {
        return amount/100;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public Long getUnitPrice() {
        return unitPrice/100;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }
}
