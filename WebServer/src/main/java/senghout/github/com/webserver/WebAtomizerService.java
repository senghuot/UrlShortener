package senghout.github.com.webserver;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
public class WebAtomizerService {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    Environment environment;

    @Value("${atomizer.service.url}")
    protected String serviceUrl;

    public String visitUrl(String tinyUrl) {
        if (tinyUrl.equals("error")) {
            return "/error";
        }
        return restTemplate.getForObject(
                serviceUrl + "/find/" + tinyUrl,
                String.class
        );
    }

    public String addUrl(String fullUrl) throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("fullUrl", fullUrl);
        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);
        return restTemplate.postForObject(serviceUrl + "/add", request, String.class);
    }
}
