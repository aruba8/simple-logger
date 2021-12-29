package ca.erik.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MessageService {

    private final static Logger logger = LoggerFactory.getLogger(MessageService.class);

    public SendMessage getMessage(Update update) {
        Message message = update.getMessage();
        Long userId = message.getFrom().getId();
        logger.info("User: {}", userId);
        Long chatId = message.getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message.getText());
        return sendMessage;
    }
}
