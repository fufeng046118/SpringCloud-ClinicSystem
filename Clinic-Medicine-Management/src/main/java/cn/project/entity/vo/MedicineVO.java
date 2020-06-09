package cn.project.entity.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MedicineVO {
    private String prescriptionTypeId;
    private Integer medicineStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;
    private String name;
    private Integer pageNo;

    public String getPrescriptionTypeId() {
        return prescriptionTypeId;
    }

    public void setPrescriptionTypeId(String prescriptionTypeId) {
        this.prescriptionTypeId = prescriptionTypeId;
    }

    public Integer getMedicineStatus() {
        return medicineStatus;
    }

    public void setMedicineStatus(Integer medicineStatus) {
        this.medicineStatus = medicineStatus;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
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
}
