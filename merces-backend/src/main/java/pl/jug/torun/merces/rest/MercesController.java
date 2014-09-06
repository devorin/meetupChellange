/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.jug.torun.merces.rest;

import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jug.torun.merces.meetup.MeetupClient;
import pl.jug.torun.merces.meetup.model.Event;
import pl.jug.torun.merces.meetup.model.EventList;
import pl.jug.torun.merces.meetup.model.EventMemberList;
import pl.jug.torun.merces.meetup.model.Member;
import pl.jug.torun.merces.model.AwardEvent;
import pl.jug.torun.merces.model.DrawStatus;
import pl.jug.torun.merces.model.ResultDraw;
import pl.jug.torun.merces.repository.AwardDictionaryRepository;
import pl.jug.torun.merces.repository.AwardEventRepository;
import pl.jug.torun.merces.repository.ResultDrawRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class MercesController {

    @Autowired
    MeetupClient meetupClient;

    @Autowired
    AwardDictionaryRepository dictionaryRepository;

    @Autowired
    AwardEventRepository awardRepository;

    @Autowired
    ResultDrawRepository resultDrawRepository;

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


    @RequestMapping(value = "/draw/{event_id}/{award_event_id}", method = RequestMethod.POST)
    public ResultDraw draw(
            @PathVariable("event_id") String eventId,
            @PathVariable("award_event_id") ObjectId awardEventId) {
        List<Member> memberList = getMembers(eventId);
        AwardEvent award = awardRepository.findOne(awardEventId);

        Random randomGenerator = new Random();
        Integer winnerIndex = randomGenerator.nextInt(memberList.size());

        Member winner = memberList.get(winnerIndex);

        ResultDraw resultDraw = new ResultDraw();
        resultDraw.setAward(award);
        resultDraw.setEventId(eventId);
        resultDraw.setMember(winner);
        resultDraw.setStatus(DrawStatus.NEW);

        resultDrawRepository.save(resultDraw);

        return resultDraw;
    }

    @RequestMapping("/awards_dictionary")
    public List<String> getAwardsDictionary() {
        List<String> dictionary = Lists.newArrayList();
        dictionaryRepository.findAll()
                .forEach(word -> dictionary.add(word.getName()));

        return dictionary;
    }

}
