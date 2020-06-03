package cn.project.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
@KeySequence(value = "SEQ_MEMBERNO", clazz = Integer.class)
@TableName(value = "MEMBER")
public class Member {
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    private String memberCardNo;
    private Integer memberLevelId;
    private String name;
    private String mobile;
    private double totalConsumption;
    private double balance;
    private double totalStoredValue;
    private Long integral;
    private Date cardOpeningDate;
    private Date expireDate;
    private Integer MemberStatus;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
    @TableField(exist = false)
    private Level level;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", memberCardNo='" + memberCardNo + '\'' +
                ", memberLevelId=" + memberLevelId +
                ", memberName='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", totalConsumption=" + totalConsumption +
                ", balance=" + balance +
                ", totalStoredValue=" + totalStoredValue +
                ", integral=" + integral +
                ", cardOpeningDate=" + cardOpeningDate +
                ", expireDate=" + expireDate +
                ", MemberStatus=" + MemberStatus +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", level=" + level +
                '}';
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
    }

    public Integer getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(Integer memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTotalStoredValue() {
        return totalStoredValue;
    }

    public void setTotalStoredValue(double totalStoredValue) {
        this.totalStoredValue = totalStoredValue;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    public Date getCardOpeningDate() {
        return cardOpeningDate;
    }

    public void setCardOpeningDate(Date cardOpeningDate) {
        this.cardOpeningDate = cardOpeningDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getMemberStatus() {
        return MemberStatus;
    }

    public void setMemberStatus(Integer memberStatus) {
        MemberStatus = memberStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
