package senghout.github.com.atomizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import senghout.github.com.atomizer.model.AddRequest;
import senghout.github.com.atomizer.model.TinyUrl;
import senghout.github.com.atomizer.model.Zoo;
import senghout.github.com.atomizer.repo.TinyUrlRepo;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        zoo = heimdall.getTestRange();
    }

    @GetMapping(value = "/find/{TinyUrl}")
    public void getUrl(@PathVariable("TinyUrl")String tinyUrl, HttpServletResponse response) throws IOException{
        TinyUrl url = repo.findByTinyUrl(tinyUrl);

        if (url == null) {
            response.sendRedirect("/error");
        }  else {
            response.sendRedirect(url.fullUrl);
        }
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public String addUrl(@RequestBody AddRequest addRequest) {
        final String tinyUrl = atomizerUtils.encodeNumber(zoo.low++);
        final TinyUrl tiny = new TinyUrl(tinyUrl, addRequest.fullUrl);
        repo.save(tiny);

        if (zoo.low == zoo.high) {
            zoo = heimdall.getTestRange();
        }
        return tinyUrl;
    }

    @GetMapping(value = "/error")
    public String error() {
        return "Invalid url given";
    }
}
