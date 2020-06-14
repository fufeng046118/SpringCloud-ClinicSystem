package cn.project.bean;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
@TableName("OrderInfo")
public class OrderInfo {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private String orderNo;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    private double amount;
    @TableField(fill = FieldFill.INSERT)
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
