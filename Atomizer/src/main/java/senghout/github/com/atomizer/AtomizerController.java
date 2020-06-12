package senghout.github.com.atomizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import senghout.github.com.atomizer.model.TinyUrl;
import senghout.github.com.atomizer.model.Zoo;
import senghout.github.com.atomizer.repo.TinyUrlRepo;

import javax.annotation.PostConstruct;

@RestController
public class AtomizerController {

    private Zoo zoo;

    @Autowired
    TinyUrlRepo repo;

    @Autowired
    Heimdall heimdall;

    @Autowired
    AtomizerUtils atomizerUtils;

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        zoo = heimdall.getNextRange();
    }

    @GetMapping(value = "/find/{TinyUrl}")
    public String getUrl(@PathVariable("TinyUrl")String tinyUrl) {
        TinyUrl url = repo.findByTinyUrl(tinyUrl);
        return url == null ? "Invalid input exception" : url.fullUrl;
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public String addUrl(@RequestBody String fullUrl) {
        final String tinyUrl = atomizerUtils.encodeNumber(zoo.low++);
        final TinyUrl tiny = new TinyUrl(tinyUrl, fullUrl);
        repo.save(tiny);

        if (zoo.low == zoo.high) {
            zoo = heimdall.getNextRange();
        }
        return tinyUrl;
    }

    @GetMapping(value = "/visit")
    public Zoo addUrl() {
        Zoo zoo = restTemplate.getForObject(
                "http://web-service/range",
                Zoo.class);
        return zoo;
    }
}
