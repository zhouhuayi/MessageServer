package com.choose.Message.bean;

public class VoterSelect {
    private Integer id;

    private String name;

    private Integer number;

    private Integer voterid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getVoterid() {
        return voterid;
    }

    public void setVoterid(Integer voterid) {
        this.voterid = voterid;
    }
}