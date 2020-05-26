package cn.project.entity;

import java.io.Serializable;

public class Medicine implements Serializable {
    private Integer id;
    private String medicineNo;
    private String barCode;
    private String medicineName;
    private String pinYinCode;
    private Integer prescriptionTypeId;
    private String prescriptionTypeName;
    private Integer medicineTypeId;
    private String medicineTypeName;
    private String medicineSpecifications;
    private Integer otc;
    private Integer medicineStatus;
    private String medicineDescription;
    private String note;
    private Long purchasePrice;
    private Long retailPrice;
    private Integer manufacturerId;
    private String manufacturerName;
    private Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicineNo() {
        return medicineNo;
    }

    public void setMedicineNo(String medicineNo) {
        this.medicineNo = medicineNo;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getPinYinCode() {
        return pinYinCode;
    }

    public void setPinYinCode(String pinYinCode) {
        this.pinYinCode = pinYinCode;
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

    public Integer getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Integer medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }

    public String getMedicineTypeName() {
        return medicineTypeName;
    }

    public void setMedicineTypeName(String medicineTypeName) {
        this.medicineTypeName = medicineTypeName;
    }

    public String getMedicineSpecifications() {
        return medicineSpecifications;
    }

    public void setMedicineSpecifications(String medicineSpecifications) {
        this.medicineSpecifications = medicineSpecifications;
    }

    public Integer getOtc() {
        return otc;
    }

    public void setOtc(Integer otc) {
        this.otc = otc;
    }

    public Integer getMedicineStatus() {
        return medicineStatus;
    }

    public void setMedicineStatus(Integer medicineStatus) {
        this.medicineStatus = medicineStatus;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getPurchasePrice() {
        return purchasePrice/100;
    }

    public void setPurchasePrice(Long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Long getRetailPrice() {
        return retailPrice/100;
    }

    public void setRetailPrice(Long retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
