package pl.jug.torun.merces.rest;

import org.bson.types.ObjectId;

public class DrawRequest {

    private String eventId;
    private ObjectId awardEventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public ObjectId getAwardEventId() {
        return awardEventId;
    }

    public void setAwardEventId(ObjectId awardEventId) {
        this.awardEventId = awardEventId;
    }
}
