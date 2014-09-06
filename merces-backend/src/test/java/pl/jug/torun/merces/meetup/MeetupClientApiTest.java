package pl.jug.torun.merces.meetup;

import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;
import pl.jug.torun.merces.meetup.exception.MeetupException;
import pl.jug.torun.merces.meetup.model.EventList;

import java.net.URISyntaxException;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MeetupClientApiTest {

    public static final String EVENT_API_URL = "EVENT_API_URL";
    public static final String EVENT_PATH = "EVENT-PATH";
    public static final String GROUP_NAME = "GROUP_NAME";
    public static final String MEETUP_URL = "MEETUP_URL";

    @Mock
    private MeetupPath meetupPath;

    @Mock
    private RestTemplate restTemplate;

    private MeetupClientApi clientUnderTest;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        clientUnderTest = new MeetupClientApi(meetupPath, restTemplate);
    }

    @Test
    public void shouldCallMeetupApi() throws Throwable {
        Map<String, String> params = Maps.newHashMap();
        params.put("group_urlname", GROUP_NAME);
        params.put("status", "upcoming,past");

        when(meetupPath.getUrl("events", params)).thenReturn(MEETUP_URL);

        clientUnderTest.getEvents(GROUP_NAME);

        verify(restTemplate).getForObject(MEETUP_URL, EventList.class);
    }

    @Test
    public void shouldThrowMeetupExceptionWhenClientHasProblem() throws Throwable {
        Map<String, String> params = Maps.newHashMap();
        params.put("group_urlname", GROUP_NAME);
        params.put("status", "upcoming,past");

        when(meetupPath.getUrl("events", params)).thenThrow(new URISyntaxException("INPUT", "REASON"));

        try {
            clientUnderTest.getEvents(GROUP_NAME);
            fail("Exception expected");
        } catch (MeetupException ex) {
            assertThat(ex.getMessage()).isEqualTo("Meetup Url not valid");
        }
    }


}