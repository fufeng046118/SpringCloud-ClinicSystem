package cn.project.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "OUTSTOCK_MEDICINE", schema = "SCOTT", catalog = "")
public class OutstockMedicine {
    private long id;
    private long outstockid;
    private long medicineid;
    private long count;
    private String lotnumber;
    private Date expirationdate;
    private Double purchaseprice;
    private Double price;
    private Medicine medicine;

    public Medicine getMedicine() {
        return medicine;
    }

    @Override
    public String toString() {
        return "OutstockMedicine{" +
                "id=" + id +
                ", outstockid=" + outstockid +
                ", medicineid=" + medicineid +
                ", count=" + count +
                ", lotnumber='" + lotnumber + '\'' +
                ", expirationdate=" + expirationdate +
                ", purchaseprice=" + purchaseprice +
                ", price=" + price +
                ", medicine=" + medicine +
                '}';
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
    @Column(name = "OUTSTOCKID", nullable = false, precision = 0)
    public long getOutstockid() {
        return outstockid;
    }

    public void setOutstockid(long outstockid) {
        this.outstockid = outstockid;
    }

    @Basic
    @Column(name = "MEDICINEID", nullable = false, precision = 0)
    public long getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(long medicineid) {
        this.medicineid = medicineid;
    }

    @Basic
    @Column(name = "COUNT", nullable = false, precision = 0)
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Basic
    @Column(name = "LOTNUMBER", nullable = false, length = 50)
    public String getLotnumber() {
        return lotnumber;
    }

    public void setLotnumber(String lotnumber) {
        this.lotnumber = lotnumber;
    }

    @Basic
    @Column(name = "EXPIRATIONDATE", nullable = true)
    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    @Basic
    @Column(name = "PURCHASEPRICE", nullable = true, precision = 0)
    public Double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    @Basic
    @Column(name = "PRICE", nullable = true, precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutstockMedicine that = (OutstockMedicine) o;
        return id == that.id &&
                outstockid == that.outstockid &&
                medicineid == that.medicineid &&
                count == that.count &&
                Objects.equals(lotnumber, that.lotnumber) &&
                Objects.equals(expirationdate, that.expirationdate) &&
                Objects.equals(purchaseprice, that.purchaseprice) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, outstockid, medicineid, count, lotnumber, expirationdate, purchaseprice, price);
    }
}
