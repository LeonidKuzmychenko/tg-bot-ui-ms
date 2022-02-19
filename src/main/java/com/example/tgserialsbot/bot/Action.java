package com.example.tgserialsbot.bot;

import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

public abstract class Action {

    @Autowired
    @Qualifier("tgMessages")
    public Map<String, String> messages;

    @Autowired
    @Qualifier("tgAnswerVariables")
    public Map<String, String> variables;

    public final KeyboardProvider keyboardProvider;
    public final AbsSender absSender;
    public final BotUserService botUserService;

    public Action(KeyboardProvider keyboardProvider, AbsSender absSender, BotUserService botUserService) {
        this.keyboardProvider = keyboardProvider;
        this.absSender = absSender;
        this.botUserService = botUserService;
    }

    public abstract void action(Update update, BotUser botUser, String chatId, String text);

    public abstract String getKey();

    @Autowired
    void add(ActionRouter actionRouter) {
        actionRouter.put(getKey(), this);
    }

    public void send(Object message) {
        try {
            if (message instanceof SendMessage) {
                this.absSender.execute((SendMessage) message);
                return;
            }
            if (message instanceof SendPhoto) {
                this.absSender.execute((SendPhoto) message);
                return;
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}