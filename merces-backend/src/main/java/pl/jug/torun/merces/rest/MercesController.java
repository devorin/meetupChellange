/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.jug.torun.merces.rest;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.jug.torun.merces.meetup.MeetupClient;
import pl.jug.torun.merces.meetup.model.Event;
import pl.jug.torun.merces.meetup.model.EventList;
import pl.jug.torun.merces.meetup.model.EventMemberList;
import pl.jug.torun.merces.meetup.model.Member;
import pl.jug.torun.merces.repository.AwardDictionaryRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MercesController {

    @Autowired
    MeetupClient meetupClient;

    @Autowired
    AwardDictionaryRepository dictionaryRepository;

    @RequestMapping("/events")
    public List<Event> getEvents(@RequestParam("groupName") String groupName) {
        EventList eventList = meetupClient.getEvents(groupName);
        return eventList.getResults();
    }

    @RequestMapping("/event_members/{event_id}")
    public List<Member> getMembers(@PathVariable("event_id") String eventId) {
        EventMemberList eventMemberList = meetupClient.getMembers(eventId);
        return eventMemberList.getResults()
                .stream()
                .map(eventMember -> eventMember.getMember())
                .collect(Collectors.toList());
    }

    @RequestMapping("/awards_dictionary")
    public List<String> getAwardsDictionary() {
        List<String> dictionary = Lists.newArrayList();
	dictionaryRepository.findAll()
		.forEach(
			word ->
				dictionary.add(
					word.getName()));
        return dictionary;
    }
}
