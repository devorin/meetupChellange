package pl.jug.torun.merces.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.jug.torun.merces.meetup.model.Member;

@Document
public class ResultDraw {

    @Id
    private DrawId id;

    private String eventId;

    private AwardEvent award;

    private DrawStatus status;

    private Member member;

    public DrawId getId() {
        return id;
    }

    public void setId(DrawId id) {
        this.id = id;
    }

    public AwardEvent getAward() {
        return award;
    }

    public void setAward(AwardEvent award) {
        this.award = award;
    }

    public DrawStatus getStatus() {
        return status;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setStatus(DrawStatus status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
