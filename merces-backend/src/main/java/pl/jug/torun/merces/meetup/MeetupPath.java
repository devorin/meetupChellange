package pl.jug.torun.merces.meetup;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

@Component
@PropertySource("classpath:config.properties")
public class MeetupPath {

    @Value("${meetup_api_url}")
    private String baseUrl;

    @Value("${meetup_api_version}")
    private String version;

    @Value("${meetup_key}")
    private String key;

    public String getUrl(String path) throws URISyntaxException {
          return getUrl(path, Collections.EMPTY_MAP);
    }

    public String getUrl(String path, Map<String, String> params) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(baseUrl);
        uriBuilder.setPath(String.format("/%s/%s", version, path));
        uriBuilder.addParameter("key", key);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), entry.getValue());
        }

        return uriBuilder.toString();
    }

}
