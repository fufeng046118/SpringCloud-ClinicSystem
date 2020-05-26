package cn.project.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//处方模板表
public class Template implements Serializable {
    private Integer id;
    private String templateName;
    private String templateNo;
    private Integer prescriptionTypeId;
    private PrescriptionType prescriptionType;
    private List<DiagnosisType> diagnosisTypeList;
    private Integer templatePermission;
    private Date createDate;
    private Employee employee;
    private String templateDescription;

    public Integer getPrescriptionTypeId() {
        return prescriptionTypeId;
    }

    public void setPrescriptionTypeId(Integer prescriptionTypeId) {
        this.prescriptionTypeId = prescriptionTypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo;
    }

    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
    }


    public List<DiagnosisType> getDiagnosisTypeList() {
        return diagnosisTypeList;
    }

    public void setDiagnosisTypeList(List<DiagnosisType> diagnosisTypeList) {
        this.diagnosisTypeList = diagnosisTypeList;
    }

    public Integer getTemplatePermission() {
        return templatePermission;
    }

    public void setTemplatePermission(Integer templatePermission) {
        this.templatePermission = templatePermission;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }
}
