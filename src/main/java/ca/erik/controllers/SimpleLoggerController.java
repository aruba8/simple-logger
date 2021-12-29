package ca.erik.controllers;

import ca.erik.bot.SimpleLoggerBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class SimpleLoggerController {
    private final SimpleLoggerBot bot;

    public SimpleLoggerController(SimpleLoggerBot simpleLoggerBot) {
        this.bot = simpleLoggerBot;
    }

    @PostMapping(value = "/")
    public BotApiMethod<?> getMessage(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }

}
