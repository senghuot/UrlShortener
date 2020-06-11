package senghout.github.com.webserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebServerController {

    @Autowired
    protected WebAtomizerService atomizerService;

    @Autowired
    protected WebHeimdallService heimdallService;

    // We are injecting an instance of WebAtomizerService into the REST API endpoints that WebServer is hosting
    public WebServerController(WebAtomizerService atomizerService, WebHeimdallService heimdallService) {
        this.atomizerService = atomizerService;
        this.heimdallService = heimdallService;
    }

    @RequestMapping(value = "/", headers = "Accept=application/json")
    public String homepage() {
        // Calls the atomizer endpoint that we want to connect to
        return atomizerService.homepage();
    }


    @RequestMapping(value = "/range", headers = "Accept=application/json")
    public String nextRange() {
        // Calls the heimdallService that we used DI to create to make a request to the heimdallService
        // and return the next range back.
        return heimdallService.nextRange();
    }
}
