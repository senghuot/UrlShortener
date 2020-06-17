package senghout.github.com.webserver;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class WebAtomizerService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    public WebAtomizerService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public String visitUrl(String tinyUrl) {
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
