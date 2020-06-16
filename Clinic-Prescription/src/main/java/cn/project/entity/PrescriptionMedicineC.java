package cn.project.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

//检查处方
@ApiModel(value = "处方（检查项目）")
@TableName(value = "checkitemprescription")
public class PrescriptionMedicineC implements Serializable {
    @ApiModelProperty(value = "主键")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "检查项目ID",example = "1")
    private Integer checkItemId;
    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private String checkItemName;
    @ApiModelProperty(value = "部位",example = "口腔")
    private String part;
    @ApiModelProperty(value = "数量",example = "1")
    private Integer quantity;
    @ApiModelProperty(value = "检查项目类型ID",example = "1")
    private Integer itemTypeId;
    @ApiModelProperty(value = "金额",example = "500")
    private Long amount;
    @ApiModelProperty(value = "备注",example = ".....")
    private String remarks;
    @ApiModelProperty(value = "处方类型ID",example = "1")
    private Integer prescriptionTypeId;
    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private String prescriptionTypeName;
    @ApiModelProperty(value = "单价",example = "60")
    private Long unitPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCheckItemId() {
        return checkItemId;
    }

    public void setCheckItemId(Integer checkItemId) {
        this.checkItemId = checkItemId;
    }

    public String getCheckItemName() {
        return checkItemName;
    }

    public void setCheckItemName(String checkItemName) {
        this.checkItemName = checkItemName;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }
}
