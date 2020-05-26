package cn.project.entity;

import java.io.Serializable;

//诊断类型表
public class DiagnosisType implements Serializable {
    private Integer id;
    private String diagnosisTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiagnosisTypeName() {
        return diagnosisTypeName;
    }

    public void setDiagnosisTypeName(String diagnosisTypeName) {
        this.diagnosisTypeName = diagnosisTypeName;
    }
}
