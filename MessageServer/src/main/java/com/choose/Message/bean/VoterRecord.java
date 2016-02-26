package com.choose.Message.bean;

import java.util.Date;

public class VoterRecord {
    private Integer id;

    private Integer voterid;

    private Long voterperson;

    private Date votertime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoterid() {
        return voterid;
    }

    public void setVoterid(Integer voterid) {
        this.voterid = voterid;
    }

    public Long getVoterperson() {
        return voterperson;
    }

    public void setVoterperson(Long voterperson) {
        this.voterperson = voterperson;
    }

    public Date getVotertime() {
        return votertime;
    }

    public void setVotertime(Date votertime) {
        this.votertime = votertime;
    }
}