package cn.project.entity;

import java.io.Serializable;

public class CheckItemType implements Serializable {
    private Integer id;
    private String itemTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }
}
