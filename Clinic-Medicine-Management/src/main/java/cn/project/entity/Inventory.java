package cn.project.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
public class Inventory {
    private long id;
    private String inventoryno;
    private Date inventorydate;
    private long employeeid;
    private Long status;
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
    @Column(name = "INVENTORYNO", nullable = true, length = 50)
    public String getInventoryno() {
        return inventoryno;
    }

    public void setInventoryno(String inventoryno) {
        this.inventoryno = inventoryno;
    }

    @Basic
    @Column(name = "INVENTORYDATE", nullable = false)
    public Date getInventorydate() {
        return inventorydate;
    }

    public void setInventorydate(Date inventorydate) {
        this.inventorydate = inventorydate;
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
    @Column(name = "STATUS", nullable = true, precision = 0)
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return id == inventory.id &&
                employeeid == inventory.employeeid &&
                Objects.equals(inventoryno, inventory.inventoryno) &&
                Objects.equals(inventorydate, inventory.inventorydate) &&
                Objects.equals(status, inventory.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inventoryno, inventorydate, employeeid, status);
    }
}
