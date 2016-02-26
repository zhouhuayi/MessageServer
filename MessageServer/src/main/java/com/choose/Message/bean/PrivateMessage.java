package com.choose.Message.bean;

import java.util.Date;

public class PrivateMessage {
    private Long id;

    private Long sendperson;

    private Long receiveperson;

    private Date sendtime;

    private Integer roomid;

    private Integer state;

    private Integer sendtype;

    private Integer receivetype;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSendperson() {
        return sendperson;
    }

    public void setSendperson(Long sendperson) {
        this.sendperson = sendperson;
    }

    public Long getReceiveperson() {
        return receiveperson;
    }

    public void setReceiveperson(Long receiveperson) {
        this.receiveperson = receiveperson;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSendtype() {
        return sendtype;
    }

    public void setSendtype(Integer sendtype) {
        this.sendtype = sendtype;
    }

    public Integer getReceivetype() {
        return receivetype;
    }

    public void setReceivetype(Integer receivetype) {
        this.receivetype = receivetype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}