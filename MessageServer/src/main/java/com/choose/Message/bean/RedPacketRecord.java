package com.choose.Message.bean;

import java.util.Date;

public class RedPacketRecord {
    private Integer id;

    private Long reviewuserid;

    private Double price;

    private Date reviewtime;

    private Integer gradeid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getReviewuserid() {
        return reviewuserid;
    }

    public void setReviewuserid(Long reviewuserid) {
        this.reviewuserid = reviewuserid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getReviewtime() {
        return reviewtime;
    }

    public void setReviewtime(Date reviewtime) {
        this.reviewtime = reviewtime;
    }

    public Integer getGradeid() {
        return gradeid;
    }

    public void setGradeid(Integer gradeid) {
        this.gradeid = gradeid;
    }
}