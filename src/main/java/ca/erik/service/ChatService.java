package ca.erik.service;

import ca.erik.model.Chat;
import org.springframework.stereotype.Service;

@Service
public interface ChatService {
    Chat findOrCreate(Chat chat);
}
