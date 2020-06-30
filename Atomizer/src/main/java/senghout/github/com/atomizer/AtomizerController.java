package senghout.github.com.atomizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senghout.github.com.atomizer.model.AddUrlInput;
import senghout.github.com.atomizer.model.TinyUrl;
import senghout.github.com.atomizer.model.Zoo;
import senghout.github.com.atomizer.repo.TinyUrlRepo;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class AtomizerController {
    private Zoo zoo;

    @Autowired
    TinyUrlRepo repo;

    @Autowired
    Heimdall heimdall;

    @Autowired
    AtomizerUtils atomizerUtils;

    private int[] order;
    private int index;

    public AtomizerController(AtomizerUtils atomizerUtils, TinyUrlRepo repo, Heimdall heimdall) {
        this.atomizerUtils = atomizerUtils;
        this.repo = repo;
        this.heimdall = heimdall;
        setupNextRange();
    }

    @GetMapping(value = "/find/{TinyUrl}")
    public String getUrl(@PathVariable("TinyUrl")String tinyUrl) {
        TinyUrl url = repo.findByTinyUrl(tinyUrl);
        return url == null ? "Invalid input exception" : url.fullUrl;
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public String addUrl(@RequestBody AddUrlInput data) {
        if (zoo == null || this.index == zoo.high - zoo.low) {
            setupNextRange();
        }
        long nextValue = generateSeedUrlValue();
        final String tinyUrl = atomizerUtils.encodeNumber(nextValue);
        final TinyUrl tiny = new TinyUrl(tinyUrl, data.fullUrl);
        repo.save(tiny);

        return tinyUrl;
    }

    private void setupNextRange() {
        this.zoo = heimdall.getNextRange();
        this.order = new int[(int)(zoo.high - zoo.low)];
        for (int i = 0; i < order.length; i++) {
            this.order[i] = i;
        }
        this.index = 0;
    }

    private long generateSeedUrlValue() {
        int min = this.index++;

        // Randomly selects an index from min (inclusive) to the max range (exclusive)
        int nextIndex = ThreadLocalRandom.current().nextInt(min, (int) (this.zoo.high - this.zoo.low));

        // Swaps the randomly selected index to be the min value which won't be used again
        int temp = this.order[min];
        this.order[min] = this.order[nextIndex];
        this.order[nextIndex] = temp;

        // Add our random value with the low range to generate a random seed to create a tiny url
        return this.zoo.low + this.order[min];
    }
}
