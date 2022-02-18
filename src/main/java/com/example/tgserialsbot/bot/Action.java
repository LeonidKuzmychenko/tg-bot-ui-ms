package com.example.tgserialsbot.bot;

import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class Action {

    public final BotUserService botUserService;

    public Action(BotUserService botUserService) {
        this.botUserService = botUserService;
    }

    public abstract void action(Update update, AbsSender absSender, BotUser botUser, String chatId, String text);

    public abstract String getKey();

    @Autowired
    void add(ActionRouter actionRouter) {
        actionRouter.put(getKey(), this);
    }

    public void send(AbsSender absSender, SendMessage sendMessage) {
        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public ReplyKeyboard defaultKeyboard() {
        return new ReplyKeyboardRemove(true);
    }

}