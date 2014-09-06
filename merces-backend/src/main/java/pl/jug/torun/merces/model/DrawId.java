package pl.jug.torun.merces.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DrawId {

    final private String eventId;
    final private String winnerName;
    final private String awardName;

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
