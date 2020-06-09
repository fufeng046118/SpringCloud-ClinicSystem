package cn.project.entity;
import java.io.Serializable;
import java.util.Date;

public class InStock implements Serializable {
    private long id;
    private String inStockNo;
    private int type;
    private int employeeId;
    private double purchasePrice;
    private double price;
    private int makeOrderId;
    private int statusId;
    private Integer auditId;
    private Date auditDate;
    private String mark;
    private int manufacturerId;
    private InStockType instocktype;
    private Employee employee;
    private Employee makeOrder;
    private Employee audit;
    private Manufacturer manufacturer;
    private Date createDate;

    @Override
    public String toString() {
        return "InStock{" +
                "id=" + id +
                ", inStockNo='" + inStockNo + '\'' +
                ", type=" + type +
                ", employeeId=" + employeeId +
                ", purchasePrice=" + purchasePrice +
                ", price=" + price +
                ", makeOrderId=" + makeOrderId +
                ", statusId=" + statusId +
                ", auditId=" + auditId +
                ", auditDate=" + auditDate +
                ", mark='" + mark + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", instocktype=" + instocktype +
                ", employee=" + employee +
                ", makeOrder=" + makeOrder +
                ", audit=" + audit +
                ", manufacturer=" + manufacturer +
                ", createDate=" + createDate +
                '}';
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInStockNo() {
        return inStockNo;
    }

    public void setInStockNo(String inStockNo) {
        this.inStockNo = inStockNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public int getMakeOrderId() {
        return makeOrderId;
    }

    public void setMakeOrderId(int makeOrderId) {
        this.makeOrderId = makeOrderId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public InStockType getInstocktype() {
        return instocktype;
    }

    public void setInstocktype(InStockType instocktype) {
        this.instocktype = instocktype;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getMakeOrder() {
        return makeOrder;
    }

    public void setMakeOrder(Employee makeOrder) {
        this.makeOrder = makeOrder;
    }

    public Employee getAudit() {
        return audit;
    }

    public void setAudit(Employee audit) {
        this.audit = audit;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
