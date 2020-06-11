package senghout.github.com.atomizer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import senghout.github.com.atomizer.model.Zoo;

public interface ZooRepo extends MongoRepository<Zoo, String> {}
