package ca.erik.service.impl;

import ca.erik.model.Chat;
import ca.erik.repository.ChatRepository;
import ca.erik.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    private final static Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat findOrCreate(Chat chat) {
        Chat dbChat = chatRepository.findFirstByTgId(chat.getTgId());
        if (dbChat == null){
            logger.debug("Chat with tgId {} not found, inserting into db", chat.getId());
            dbChat = chatRepository.insert(chat);
        }
        return dbChat;
    }
}
