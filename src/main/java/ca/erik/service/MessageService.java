package ca.erik.service;

import ca.erik.model.Chat;
import ca.erik.model.Text;
import ca.erik.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;

@Service
public record MessageService(UsersService usersService, TextService textService,
                             ChatService chatService) {

    private final static Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final static String PRIVATE_TYPE = "private";
    private final static String GROUP_TYPE = "group";
    private final static String CHANNEL_TYPE = "channel";
    private final static String SUPERGROUP_TYPE = "supergroup";

    public SendMessage handleUpdate(Update update) {
        logger.info("Update from chatId: {}, type: {}", update.getMessage().getChat().getId(), update.getMessage().getChat().getType());
        SendMessage sendMessage = null;
        switch (update.getMessage().getChat().getType()) {
            case SUPERGROUP_TYPE, GROUP_TYPE -> sendMessage = handleGroupUpdate(update);
            case PRIVATE_TYPE -> sendMessage = handlePrivateChat(update);
            case CHANNEL_TYPE -> sendMessage = handleChannel(update);
        }
        return sendMessage;
    }

    private SendMessage handleChannel(Update update) {
        return null;
    }

    private SendMessage handlePrivateChat(Update update) {
        Message message = update.getMessage();
        logMessage(message);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChat().getId().toString());
        sendMessage.setText(message.getText());
        return sendMessage;
    }

    private SendMessage handleGroupUpdate(Update update) {
        Message message = update.getMessage();
        logMessage(message);
        return null;
    }

    private void logMessage(Message message) {
        Chat messageChat = chatFromMessage(message);
        Chat chat = chatService.findOrCreate(messageChat);
        logger.info("Chat: {}", chat);
        User messageUser = userFromMessage(message);
        User user = usersService.findOrCreate(messageUser);
        logger.info("User: {}", user);
        Text text = textFromMessage(message);
        textService.create(text);
    }

    private User userFromMessage(Message message) {
        User user = new User();
        user.setFirstName(message.getFrom().getFirstName());
        user.setLastName(message.getFrom().getLastName());
        user.setUsername(message.getFrom().getUserName());
        user.setTgId(message.getFrom().getId());
        user.setLangCode(message.getFrom().getLanguageCode());
        user.setIsBot(message.getFrom().getIsBot());
        user.setCreated(new Date());
        return user;
    }

    private Text textFromMessage(Message message) {
        Text text = new Text();
        text.setChatId(message.getChatId());
        text.setFromUserId(message.getFrom().getId());
        text.setText(message.getText());
        text.setDate(new Date(message.getDate() * 1000L));
        text.setMessageId(message.getMessageId());
        return text;
    }

    private Chat chatFromMessage(Message message) {
        Chat chat = new Chat();
        chat.setTgId(message.getChatId());
        chat.setTitle(message.getChat().getTitle());
        chat.setUserName(message.getChat().getUserName());
        chat.setType(message.getChat().getType());
        return chat;
    }

}
