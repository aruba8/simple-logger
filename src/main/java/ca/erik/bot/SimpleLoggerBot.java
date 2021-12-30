package ca.erik.bot;

import ca.erik.BotConfig;
import ca.erik.service.MessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class SimpleLoggerBot extends TelegramWebhookBot {

    private final MessageService messageService;
    private final BotConfig botConfig;

    public SimpleLoggerBot(MessageService messageService, BotConfig botConfig) {
        this.messageService = messageService;
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return messageService.handleUpdate(update);
    }

    @Override
    public String getBotPath() {
        return botConfig.getWebHookPath();
    }
}
