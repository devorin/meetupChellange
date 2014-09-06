package pl.jug.torun.merces.meetup;

import pl.jug.torun.merces.meetup.exception.MeetupException;
import pl.jug.torun.merces.meetup.model.Event;
import pl.jug.torun.merces.meetup.model.EventList;
import pl.jug.torun.merces.meetup.model.EventMember;

import java.util.List;

public interface MeetupClient {

    public EventList getEvents(String groupName) throws MeetupException;

    public List<EventMember> getMembers(Event event);

}
