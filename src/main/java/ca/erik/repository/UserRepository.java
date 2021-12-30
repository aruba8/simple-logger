package ca.erik.repository;

import ca.erik.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findFirstByTgId(Long tgId);
}
