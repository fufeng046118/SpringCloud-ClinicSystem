package cn.project.entity;

import java.io.Serializable;

public class InStockType implements Serializable {
    private long id;
    private String typeName;

    @Override
    public String toString() {
        return "InStockType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
