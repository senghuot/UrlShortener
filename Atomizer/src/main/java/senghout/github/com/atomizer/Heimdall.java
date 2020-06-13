package senghout.github.com.atomizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senghout.github.com.atomizer.model.Zoo;
import senghout.github.com.atomizer.repo.ZooRepo;
import java.util.Random;

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

    /**
     * TODO: get random number to avoid conflicts of numbers as our count keeper always starts at 0
     */
    public Zoo getTestRange() {
        int rand = new Random().nextInt(100000000);
        Zoo zoo = new Zoo();
        zoo.low = rand;
        zoo.high = rand + INCREMENT;
        return zoo;
    }
}
