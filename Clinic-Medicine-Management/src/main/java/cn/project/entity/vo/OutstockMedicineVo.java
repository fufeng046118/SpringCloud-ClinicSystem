package cn.project.entity.vo;

import java.io.File;
import java.sql.Date;

public class OutstockMedicineVo {
    private Integer id;
    private Integer outstockid;
    private Integer medicineid;
    private Integer count;
    private String lotnumber;
    private Date expirationdate;
    private Float purchaseprice;
    private Float price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOutstockid() {
        return outstockid;
    }

    public void setOutstockid(Integer outstockid) {
        this.outstockid = outstockid;
    }

    public Integer getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(Integer medicineid) {
        this.medicineid = medicineid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLotnumber() {
        return lotnumber;
    }

    public void setLotnumber(String lotnumber) {
        this.lotnumber = lotnumber;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public Float getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Float purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
