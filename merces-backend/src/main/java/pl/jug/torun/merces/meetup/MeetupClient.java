package pl.jug.torun.merces.meetup;

import pl.jug.torun.merces.meetup.exception.MeetupException;
import pl.jug.torun.merces.meetup.model.EventList;
import pl.jug.torun.merces.meetup.model.EventMemberList;

public interface MeetupClient {

    public EventList getEvents(String groupName) throws MeetupException;

    public EventMemberList getMembers(String eventId);

}
