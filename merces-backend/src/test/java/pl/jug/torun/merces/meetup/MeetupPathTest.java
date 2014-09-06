package pl.jug.torun.merces.meetup;

import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class MeetupPathTest {

    public static final String PATH = "/PATH";
    public static final String BASE_URL = "https://BASE_URL";
    public static final String KEY = "KEY";
    public static final String VERSION = "VERSION";
    private MeetupPath meetupPath = new MeetupPath();

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(meetupPath, "baseUrl", BASE_URL, String.class);
        ReflectionTestUtils.setField(meetupPath, "key", KEY, String.class);
        ReflectionTestUtils.setField(meetupPath, "version", VERSION, String.class);
    }

    @Test
    public void shouldReturnUrlWithKeyAndPath() throws Throwable {
        String url = meetupPath.getUrl(PATH);
        assertThat(url).isEqualTo(String.format("%s/%s/%s?key=%s", BASE_URL, VERSION, PATH, KEY));
    }

    @Test
    public void shouldReturnUrlWithKeyAndPathAndParams() throws Throwable {
        Map<String, String> params = Maps.newHashMap();
        params.put("KEY", "VALUE");

        String url = meetupPath.getUrl(PATH, params);
        assertThat(url).isEqualTo(String.format("%s/%s/%s?key=%s&KEY=VALUE", BASE_URL, VERSION, PATH, KEY));
    }

}