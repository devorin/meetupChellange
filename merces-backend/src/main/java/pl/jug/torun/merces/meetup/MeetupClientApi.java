package pl.jug.torun.merces.meetup;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.jug.torun.merces.meetup.exception.MeetupException;
import pl.jug.torun.merces.meetup.model.Event;
import pl.jug.torun.merces.meetup.model.EventList;
import pl.jug.torun.merces.meetup.model.EventMember;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Service
public class MeetupClientApi implements MeetupClient {

    @Autowired
    MeetupPath meetupPath;

    private RestTemplate restTemplate = new RestTemplate();

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
    public List<EventMember> getMembers(Event event) {

        throw new UnsupportedOperationException("Not implemented yet");
    }

}
