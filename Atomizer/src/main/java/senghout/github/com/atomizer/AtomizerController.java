package senghout.github.com.atomizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import senghout.github.com.atomizer.model.TinyUrl;
import senghout.github.com.atomizer.repo.TinyUrlRepo;

@RestController
public class AtomizerController {

    @Autowired
    Environment environment;

    @Autowired
    TinyUrlRepo repo;

    @RequestMapping(value = "/tst")
    public String tst() {
        return "Atomizer instance: " + environment.getProperty("local.server.port");
    }

    @GetMapping(value = "/{TinyUrl}")
    public String getUrl(@PathVariable("TinyUrl")String tinyUrl) {
        return repo.findByTinyUrl(tinyUrl).fullUrl;
    }

    @PostMapping(value = "/", consumes = {"application/json"})
    public String addUrl(@RequestBody TinyUrl tinyUrl) {
        tinyUrl.tinyUrl = AtomizerUtils.encodeNumber(Math.abs(tinyUrl.fullUrl.hashCode()));
        repo.save(tinyUrl);

        System.out.println("-----------------------------");
        System.out.println(tinyUrl);
        System.out.println("-----------------------------");

        return tinyUrl.tinyUrl;
    }
}
