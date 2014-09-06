package pl.jug.torun.merces.meetup.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {

    private String name;

    @JsonProperty("member_id")
    private Integer memberId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
