package cn.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(value = "处方药（中）")
@TableName(value = "chinesemedicineprescription")
public class PrescriptionMedicineZ implements Serializable {
    @ApiModelProperty(value = "主键")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "药品ID",example = "1")
    private Integer medicineId;
    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private String medicineName;
    @ApiModelProperty(value = "单次用量",example = "30g")
    private String singleDose;
    @ApiModelProperty(value = "用法ID",example = "1")
    private Integer usageId;
    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private String usageName;
    @ApiModelProperty(value = "频度",example = "一天一次")
    private String frequency;
    @ApiModelProperty(value = "总天数",example = "2")
    private Integer totalDays;
    @ApiModelProperty(value = "总用量",example = "100g")
    @TableField(exist = false)
    private String totalDosage;
    @ApiModelProperty(value = "处方类型ID",example = "1")
    private Integer prescriptionTypeId;
    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private String prescriptionTypeName;
    @ApiModelProperty(value = "剂",example = "2")
    private Integer agent;
    @ApiModelProperty(value = "单价",example = "50")
    private Long unitPrice;
    @ApiModelProperty(value = "金额",example = "500")
    private Long amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getSingleDose() {
        return singleDose;
    }

    public void setSingleDose(String singleDose) {
        this.singleDose = singleDose;
    }

    public Integer getUsageId() {
        return usageId;
    }

    public void setUsageId(Integer usageId) {
        this.usageId = usageId;
    }

    public String getUsageName() {
        return usageName;
    }

    public void setUsageName(String usageName) {
        this.usageName = usageName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public String getTotalDosage() {
        return totalDosage;
    }

    public void setTotalDosage(String totalDosage) {
        this.totalDosage = totalDosage;
    }

    public Integer getPrescriptionTypeId() {
        return prescriptionTypeId;
    }

    public void setPrescriptionTypeId(Integer prescriptionTypeId) {
        this.prescriptionTypeId = prescriptionTypeId;
    }

    public String getPrescriptionTypeName() {
        return prescriptionTypeName;
    }

    public void setPrescriptionTypeName(String prescriptionTypeName) {
        this.prescriptionTypeName = prescriptionTypeName;
    }

    public Integer getAgent() {
        return agent;
    }

    public void setAgent(Integer agent) {
        this.agent = agent;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
