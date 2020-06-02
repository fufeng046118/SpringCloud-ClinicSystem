package cn.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "处方和附加费用的关联实体类")
@TableName(value = "prescription_additionalfees")
public class Prescription_AdditionalFees {
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(example = "1",value = "处方ID")
    private Integer prescriptionId;
    @ApiModelProperty(example = "1",value = "处方类型ID")
    private Integer prescriptionTypeId;
    @ApiModelProperty(example = "1",value = "附加费用ID")
    private Integer additionfeesId;
    @ApiModelProperty(example = "1",value = "数量")
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Integer getPrescriptionTypeId() {
        return prescriptionTypeId;
    }

    public void setPrescriptionTypeId(Integer prescriptionTypeId) {
        this.prescriptionTypeId = prescriptionTypeId;
    }

    public Integer getAdditionfeesId() {
        return additionfeesId;
    }

    public void setAdditionfeesId(Integer additionfeesId) {
        this.additionfeesId = additionfeesId;
    }
}
