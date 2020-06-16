package cn.project.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "INVENTORY_MEDICINE", schema = "SCOTT", catalog = "")
public class InventoryMedicine {
    private long id;
    private long inventoryid;
    private long medicineid;
    private Long nowstock;
    private Long inventorystock;
    private Long difference;
    private String mark;
    private Medicine medicine;

    public long getInventoryid() {
        return inventoryid;
    }

    public void setInventoryid(long inventoryid) {
        this.inventoryid = inventoryid;
    }

    public long getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(long medicineid) {
        this.medicineid = medicineid;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOWSTOCK", nullable = true, precision = 0)
    public Long getNowstock() {
        return nowstock;
    }

    public void setNowstock(Long nowstock) {
        this.nowstock = nowstock;
    }

    @Basic
    @Column(name = "INVENTORYSTOCK", nullable = true, precision = 0)
    public Long getInventorystock() {
        return inventorystock;
    }

    public void setInventorystock(Long inventorystock) {
        this.inventorystock = inventorystock;
    }

    @Basic
    @Column(name = "DIFFERENCE", nullable = true, precision = 0)
    public Long getDifference() {
        return difference;
    }

    public void setDifference(Long difference) {
        this.difference = difference;
    }

    @Basic
    @Column(name = "MARK", nullable = true, length = 50)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryMedicine that = (InventoryMedicine) o;
        return id == that.id &&
                Objects.equals(nowstock, that.nowstock) &&
                Objects.equals(inventorystock, that.inventorystock) &&
                Objects.equals(difference, that.difference) &&
                Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nowstock, inventorystock, difference, mark);
    }
}
