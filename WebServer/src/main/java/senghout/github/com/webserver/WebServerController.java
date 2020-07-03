package senghout.github.com.webserver;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class WebServerController {

    @Autowired
    protected WebAtomizerService atomizerService;

    // We are injecting an instance of WebAtomizerService into the REST API endpoints that WebServer is hosting
    public WebServerController(WebAtomizerService atomizerService) {
        this.atomizerService = atomizerService;
    }

    @RequestMapping(value = "/find/{tinyUrl}", method = GET)
    public void find(@PathVariable String tinyUrl, HttpServletResponse response) throws IOException {
        // Calls the atomizer endpoint that we want to connect to
        // TODO implement an actual value from the mapping request
        String fullUrl = atomizerService.visitUrl(tinyUrl);
        response.setHeader("Location", fullUrl);
        response.setStatus(302);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public String updatePerson(@RequestBody TinyUrl url, HttpServletResponse response) throws JSONException {
        String res = atomizerService.addUrl(url.fullUrl);
        return res;
    }
}
