package cn.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
@ApiModel(value = "处方实体类")
public class Prescription implements Serializable {
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "患者ID",example = "1")
    private Integer patientId;
    @ApiModelProperty(value = "接诊类型(1:初诊；2:复诊)",example = "1")
    private Integer consultationTypeId;
    @ApiModelProperty(value = "员工ID(医生ID)",example = "1")
    private Integer employeeId;
    @ApiModelProperty(hidden = true)
    private Date date;
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getConsultationTypeId() {
        return consultationTypeId;
    }

    public void setConsultationTypeId(Integer consultationTypeId) {
        this.consultationTypeId = consultationTypeId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
