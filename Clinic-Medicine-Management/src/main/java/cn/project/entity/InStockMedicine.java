package cn.project.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class InStockMedicine implements Serializable {
    private long id;
    private int inStockId;
    private int medicineId;
    private long count;
    private String lotNumber;
    private Date expirationDate;
    private double purchasePrice;
    private double price;
    private Medicine medicine;

    @Override
    public String toString() {
        return "InStockMedicine{" +
                "id=" + id +
                ", inStockId=" + inStockId +
                ", medicineId=" + medicineId +
                ", count=" + count +
                ", lotNumber='" + lotNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", purchasePrice=" + purchasePrice +
                ", price=" + price +
                ", medicine=" + medicine +
                '}';
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getInStockId() {
        return inStockId;
    }

    public void setInStockId(int inStockId) {
        this.inStockId = inStockId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
