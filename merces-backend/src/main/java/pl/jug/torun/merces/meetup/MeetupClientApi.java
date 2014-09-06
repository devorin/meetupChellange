package pl.jug.torun.merces.meetup;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.jug.torun.merces.meetup.exception.MeetupException;
import pl.jug.torun.merces.meetup.model.EventList;
import pl.jug.torun.merces.meetup.model.EventMemberList;

import java.net.URISyntaxException;
import java.util.Map;

@Service
public class MeetupClientApi implements MeetupClient {

    private MeetupPath meetupPath;

    private RestTemplate restTemplate;

    @Autowired
    public MeetupClientApi(MeetupPath meetupPath) {
        this(meetupPath, new RestTemplate());
    }

    public MeetupClientApi(MeetupPath meetupPath, RestTemplate restTemplate) {
        this.meetupPath = meetupPath;
        this.restTemplate = restTemplate;
    }

    @Override
    public EventList getEvents(String groupName) {
        Map<String, String> params = Maps.newHashMap();
        params.put("group_urlname", groupName);
        params.put("status", "upcoming,past");

        String url;
        try {
            url = meetupPath.getUrl("events", params);
        } catch (URISyntaxException e) {
            throw new MeetupException("Meetup Url not valid", e);
        }

        System.out.println("GET " + url);
        return restTemplate.getForObject(url, EventList.class);
    }

    @Override
    public EventMemberList getMembers(String eventId) {

        Map<String, String> params = Maps.newHashMap();
        params.put("event_id", eventId);
        params.put("rsvp", "yes");

        String url;
        try {
            url = meetupPath.getUrl("rsvps", params);
        } catch (URISyntaxException e) {
            throw new MeetupException("Meetup Url not valid", e);
        }

        System.out.println("GET " + url);
        return restTemplate.getForObject(url, EventMemberList.class);
    }

}
