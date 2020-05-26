package cn.project.entity;

import java.io.Serializable;

//处方类型表
public class PrescriptionType implements Serializable {
    private Integer id;
    private String prescriptionTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrescriptionTypeName() {
        return prescriptionTypeName;
    }

    public void setPrescriptionTypeName(String prescriptionTypeName) {
        this.prescriptionTypeName = prescriptionTypeName;
    }
}
