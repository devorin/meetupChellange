package pl.jug.torun.merces.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AwardEvent {

    @Id
    private ObjectId id;

    private String stringId;

    private String name;

    private String eventId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getStringId() {
        return stringId;
    }

    public AwardEvent setStringId(String stringId) {
        this.stringId = stringId;
        return this;
    }
}
