package cn.project.entity;

import java.io.Serializable;

public class AdditionalFees implements Serializable {
    private Integer id;
    private String additionalFeesName;
    private Long price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdditionalFeesName() {
        return additionalFeesName;
    }

    public void setAdditionalFeesName(String additionalFeesName) {
        this.additionalFeesName = additionalFeesName;
    }

    public Long getPrice() {
        return price/100;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
