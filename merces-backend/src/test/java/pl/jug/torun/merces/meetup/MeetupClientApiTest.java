package pl.jug.torun.merces.meetup;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class MeetupClientApiTest {

    public static final String EVENT_API_URL = "EVENT_API_URL";
    public static final String EVENT_PATH = "EVENT-PATH";
    public static final String GROUP_NAME = "GROUP_NAME";
    @Mock
    private MeetupPath meetupPath;

    @InjectMocks
    private MeetupClientApi clientApi;

    @Test
    public void shouldReturnEmptyEventList() throws Throwable {
        when(meetupPath.getUrl(EVENT_PATH)).thenReturn(EVENT_API_URL);
        clientApi.getEvents(GROUP_NAME);
    }

}