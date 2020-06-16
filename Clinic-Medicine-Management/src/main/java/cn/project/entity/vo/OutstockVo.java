package cn.project.entity.vo;

import sun.dc.pr.PRError;

public class OutstockVo {
    private Integer geo;
    private Integer typeId;
    private Integer statusId;
    private String outstockno;

    public Integer getGeo() {
        return geo;
    }

    public void setGeo(Integer geo) {
        this.geo = geo;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getOutstockno() {
        return outstockno;
    }

    public void setOutstockno(String outstockno) {
        this.outstockno = outstockno;
    }
}
