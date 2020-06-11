package senghout.github.com.heimdall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senghout.github.com.heimdall.model.Zoo;
import senghout.github.com.heimdall.repo.ZooRepo;

@RestController
public class HeimdallController {
    @Autowired
    ZooRepo repo;

    private static final long INCREMENT = 10000000;

    /**
     * TODO: if no next range than new range needs to be inserted
     * @return returns a range consist of low and high
     */
    @GetMapping(value = "/")
    public Zoo getNextRange() {
        final Zoo newZoo = repo.findAll().get(0);
        newZoo.low += INCREMENT;
        newZoo.high += INCREMENT;
        repo.save(newZoo);
        return newZoo;
    }
}
