package cn.project.pojo;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;

@KeySequence(value = "SEQ_LEVELID", clazz = Integer.class)
@TableName(value = "LEVEL")
public class Level {
    private Integer id;
    private String memberLevel;
    private String memberName;
    private double discount;

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                ", memberLevel='" + memberLevel + '\'' +
                ", memberName='" + memberName + '\'' +
                ", discount=" + discount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
