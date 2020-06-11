package senghout.github.com.atomizer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import senghout.github.com.atomizer.model.TinyUrl;

public interface TinyUrlRepo extends MongoRepository<TinyUrl, String> {
    TinyUrl findByTinyUrl(final String tinyUrl);
}