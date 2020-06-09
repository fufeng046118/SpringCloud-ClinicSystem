package cn.project.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Objects;

@Entity
public class Outstock {
    private long id;
    private String outstockno;
    private long type;
    private long employeeid;
    private double purchaseprice;
    private double price;
    private long makeorderid;
    private long statusid;
    private Long auditid;
    private Time auditdate;
    private String mark;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "OUTSTOCKNO", nullable = false, length = 50)
    public String getOutstockno() {
        return outstockno;
    }

    public void setOutstockno(String outstockno) {
        this.outstockno = outstockno;
    }

    @Basic
    @Column(name = "TYPE", nullable = false, precision = 0)
    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    @Basic
    @Column(name = "EMPLOYEEID", nullable = false, precision = 0)
    public long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(long employeeid) {
        this.employeeid = employeeid;
    }

    @Basic
    @Column(name = "PURCHASEPRICE", nullable = false, precision = 0)
    public double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    @Basic
    @Column(name = "PRICE", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "MAKEORDERID", nullable = false, precision = 0)
    public long getMakeorderid() {
        return makeorderid;
    }

    public void setMakeorderid(long makeorderid) {
        this.makeorderid = makeorderid;
    }

    @Basic
    @Column(name = "STATUSID", nullable = false, precision = 0)
    public long getStatusid() {
        return statusid;
    }

    public void setStatusid(long statusid) {
        this.statusid = statusid;
    }

    @Basic
    @Column(name = "AUDITID", nullable = true, precision = 0)
    public Long getAuditid() {
        return auditid;
    }

    public void setAuditid(Long auditid) {
        this.auditid = auditid;
    }

    @Basic
    @Column(name = "AUDITDATE", nullable = true)
    public Time getAuditdate() {
        return auditdate;
    }

    public void setAuditdate(Time auditdate) {
        this.auditdate = auditdate;
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
        Outstock outstock = (Outstock) o;
        return id == outstock.id &&
                type == outstock.type &&
                employeeid == outstock.employeeid &&
                Double.compare(outstock.purchaseprice, purchaseprice) == 0 &&
                Double.compare(outstock.price, price) == 0 &&
                makeorderid == outstock.makeorderid &&
                statusid == outstock.statusid &&
                Objects.equals(outstockno, outstock.outstockno) &&
                Objects.equals(auditid, outstock.auditid) &&
                Objects.equals(auditdate, outstock.auditdate) &&
                Objects.equals(mark, outstock.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, outstockno, type, employeeid, purchaseprice, price, makeorderid, statusid, auditid, auditdate, mark);
    }
}
