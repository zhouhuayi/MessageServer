package com.choose.Message.bean;

import java.util.Date;

public class Blog {
    private Long id;

    private String content;

    private Date sendtime;

    private Long sendperson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Long getSendperson() {
        return sendperson;
    }

    public void setSendperson(Long sendperson) {
        this.sendperson = sendperson;
    }
}