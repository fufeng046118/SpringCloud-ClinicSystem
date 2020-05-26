package cn.project.entity;

import java.io.Serializable;

public class MedicineUsage implements Serializable {
    private Integer id;
    private String usageName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsageName() {
        return usageName;
    }

    public void setUsageName(String usageName) {
        this.usageName = usageName;
    }
}
