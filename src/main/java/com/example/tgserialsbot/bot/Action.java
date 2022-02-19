package com.example.tgserialsbot.bot;

import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import com.example.tgserialsbot.bot.services.MessageProvider;
import com.example.tgserialsbot.bot.services.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Slf4j
public abstract class Action {

    @Autowired
    @Qualifier("tgMessages")
    public Map<String, String> messages;

    @Autowired
    @Qualifier("tgAnswerVariables")
    public Map<String, String> variables;

    public final MessageSender messageSender;
    public final MessageProvider messageProvider;
    public final KeyboardProvider keyboardProvider;
    public final BotUserService botUserService;

    protected Action(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        this.messageSender = messageSender;
        this.messageProvider = messageProvider;
        this.keyboardProvider = keyboardProvider;
        this.botUserService = botUserService;
    }

    public void run(Update update, BotUser botUser, String chatId, String text) {
        log.info("__________________________________________");
        log.info("Start action '{}'", getClass().getSimpleName());
        log.info("With command = '{}'", botUser.getCommand());
        log.info("With message = '{}'", text);
        action(update, botUser, chatId, text);
        log.info("End action '{}'", getClass().getSimpleName());
    }

    protected abstract void action(Update update, BotUser botUser, String chatId, String text);

    public abstract String getKey();

    @Autowired
    void add(ActionRouter actionRouter) {
        actionRouter.put(getKey(), this);
    }
}