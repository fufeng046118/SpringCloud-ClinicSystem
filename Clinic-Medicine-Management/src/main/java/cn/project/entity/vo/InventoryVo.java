package cn.project.entity.vo;

import java.util.Date;

public class InventoryVo {
    private Integer geo;
    private Date inventorydate1;
    private Date inventorydate2;
    private String inventoryno;
    private Integer status;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGeo() {
        return geo;
    }

    public void setGeo(Integer geo) {
        this.geo = geo;
    }

    public Date getInventorydate1() {
        return inventorydate1;
    }

    public void setInventorydate1(Date inventorydate1) {
        this.inventorydate1 = inventorydate1;
    }

    public Date getInventorydate2() {
        return inventorydate2;
    }

    public void setInventorydate2(Date inventorydate2) {
        this.inventorydate2 = inventorydate2;
    }

    public String getInventoryno() {
        return inventoryno;
    }

    public void setInventoryno(String inventoryno) {
        this.inventoryno = inventoryno;
    }
}
