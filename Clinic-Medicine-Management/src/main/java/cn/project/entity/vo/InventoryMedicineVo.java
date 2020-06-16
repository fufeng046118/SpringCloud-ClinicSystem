package cn.project.entity.vo;

public class InventoryMedicineVo {
    private Integer geo;
    private Integer inventorystock;
    private Integer difference;
    private String mark;

    public Integer getGeo() {
        return geo;
    }

    public void setGeo(Integer geo) {
        this.geo = geo;
    }

    public Integer getInventorystock() {
        return inventorystock;
    }

    public void setInventorystock(Integer inventorystock) {
        this.inventorystock = inventorystock;
    }

    public Integer getDifference() {
        return difference;
    }

    public void setDifference(Integer difference) {
        this.difference = difference;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
