package pl.jug.torun.merces.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class DrawId implements Serializable {

    private String eventId;
    private String winnerName;
    private String awardName;

    public DrawId() {
    }

    public DrawId(String eventId, String winnerName, String awardName) {
        this.eventId = eventId;
        this.winnerName = winnerName;
        this.awardName = awardName;
    }

    public String getEventId() {
        return eventId;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public String getAwardName() {
        return awardName;
    }
}
