package senghout.github.com.atomizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senghout.github.com.atomizer.model.Zoo;
import senghout.github.com.atomizer.repo.ZooRepo;

@Component
public class Heimdall {

    @Autowired
    ZooRepo repo;

    private static final long INCREMENT = 10000000;

    /**
     * TODO: if no next range than new range needs to be inserted
     * @return returns a range consist of low and high
     */
    public Zoo getNextRange() {
        final Zoo newZoo = repo.findAll().get(0);
        newZoo.low += INCREMENT;
        newZoo.high += INCREMENT;
        repo.save(newZoo);
        return newZoo;
    }
}
