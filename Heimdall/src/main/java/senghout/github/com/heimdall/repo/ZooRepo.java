package senghout.github.com.heimdall.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import senghout.github.com.heimdall.model.Zoo;

public interface ZooRepo extends MongoRepository<Zoo, String> {}
