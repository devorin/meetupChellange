/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.jug.torun.merces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.jug.torun.merces.meetup.MeetupClient;
import pl.jug.torun.merces.meetup.model.EventList;

@RestController
public class MercesController {

    @Autowired
    MeetupClient meetupClient;

    @RequestMapping("/events")
    public EventList getEvents(@RequestParam("groupName") String groupName) {
        return meetupClient.getEvents(groupName);
    }
}
