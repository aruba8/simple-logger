package ca.erik.repository;

import ca.erik.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<Chat, Long> {
    Chat findFirstByTgId(Long tgId);
}
