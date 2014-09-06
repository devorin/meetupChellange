package pl.torun.jug.hackaton.merces.controller;

import org.apache.http.client.methods.HttpGet;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;
import pl.torun.jug.hackaton.merces.model.Event;
import pl.torun.jug.hackaton.merces.model.User;

import javax.management.RuntimeErrorException;
import java.util.List;

public class EventController {
    private static final String REST_SERVER_URL = "http://localhost:8080/";
    private String eventListUrl = REST_SERVER_URL + "events?groupName=Torun-JUG";
    private String eventMemberListUrl = REST_SERVER_URL + "event_members/";

    @SuppressWarnings("unchecked")
    public List<Event> getAllEvents() {
            return (List<Event>) doRequest(createRequest(eventListUrl), HttpGet.METHOD_NAME,
                    new GenericType<List<Event>>() {
            });
        }

    @SuppressWarnings("unchecked")
    public List<User> getEventUsers(String id) {
            return (List<User>) doRequest(createRequest(eventMemberListUrl, id), HttpGet.METHOD_NAME,
                    new GenericType<List<User>>() {
            });
        }

    private ClientRequest createRequest(String url) {
        return new ClientRequest(url);
    }

    private ClientRequest createRequest(String url, String id) {
        return new ClientRequest(url+id);
    }

    private Object doRequest(ClientRequest cr, String httpMethod, GenericType returnType) {
        ClientResponse r = null;
        Object serverResponseBody = null;
        try {
//            System.out.println("PERFORM A "+ httpMethod + " ON " + cr.getUri());
            r = cr.httpMethod(httpMethod);
            int status = r.getStatus();
            if ( status >= 500) {
                handleError("Server is having a bad time with this request, try again later...");
            } else if (status == 404) {
                handleError("Framework with ID " + cr.getPathParameters().get("id") + " not found.");
            } else if (r.getStatus() >= 300){
                handleError("The server responded with an unexpected status: "+ r.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleError("Unknown error: " + e.getMessage());
        }
        if (returnType != null)
            serverResponseBody = r.getEntity(returnType);
        cr.clear();
        return serverResponseBody;
    }

    private void handleError(String message) {
        throw new RuntimeErrorException(new Error(message));
    }

}
