package pl.jug.torun.merces.meetup.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventMemberList {

    private List<EventMember> results;

    public List<EventMember> getResults() {
        return results;
    }

    public void setResults(List<EventMember> results) {
        this.results = results;
    }
}
