package cn.project.entity;

public class Prescription_AdditionalFees {
    private Integer id;
    private Integer prescriptionId;
    private Integer prescriptionTypeId;
    private Integer additionfeesId;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Integer getPrescriptionTypeId() {
        return prescriptionTypeId;
    }

    public void setPrescriptionTypeId(Integer prescriptionTypeId) {
        this.prescriptionTypeId = prescriptionTypeId;
    }

    public Integer getAdditionfeesId() {
        return additionfeesId;
    }

    public void setAdditionfeesId(Integer additionfeesId) {
        this.additionfeesId = additionfeesId;
    }
}
