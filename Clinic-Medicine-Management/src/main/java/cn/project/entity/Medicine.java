package cn.project.entity;

import java.io.Serializable;
import java.util.Date;

public class Medicine implements Serializable {
    private Integer id;
    private String medicineNo;
    private String barCode;
    private String medicineName;
    private String pinYinCode;
    private PrescriptionType prescriptionType;
    private String medicineSpecifications;
    private int medicineStatus;
    private String medicineDescription;
    private double purchasePrice;
    private double retailPrice;
    private Manufacturer manufacturer;
    private Long stock;
    private Date createTime;
/*    private int prescriptionTypeId;
    private int name;*/

/*    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPrescriptionTypeId() {
        return prescriptionTypeId;
    }

    public void setPrescriptionTypeId(int prescriptionTypeId) {
        this.prescriptionTypeId = prescriptionTypeId;
    }*/

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", medicineNo='" + medicineNo + '\'' +
                ", barCode='" + barCode + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", pinYinCode='" + pinYinCode + '\'' +
                ", prescriptionType=" + prescriptionType +
                ", medicineSpecifications='" + medicineSpecifications + '\'' +
                ", medicineStatus=" + medicineStatus +
                ", medicineDescription='" + medicineDescription + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", retailPrice=" + retailPrice +
                ", manufacturer=" + manufacturer +
                ", stock=" + stock +
                ", createTime=" + createTime +
                '}';
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

    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    public String getMedicineSpecifications() {
        return medicineSpecifications;
    }

    public void setMedicineSpecifications(String medicineSpecifications) {
        this.medicineSpecifications = medicineSpecifications;
    }

    public int getMedicineStatus() {
        return medicineStatus;
    }

    public void setMedicineStatus(int medicineStatus) {
        this.medicineStatus = medicineStatus;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
