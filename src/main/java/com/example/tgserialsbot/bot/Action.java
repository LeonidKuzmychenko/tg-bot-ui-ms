package com.example.tgserialsbot.bot;

import com.example.tgserialsbot.bot.services.BotUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.bots.AbsSender;

public abstract class Action {

    public final BotUserService botUserService;

    public Action(BotUserService botUserService) {
        this.botUserService = botUserService;
    }

    public abstract void action(Update update, AbsSender absSender);

    public abstract String getKey();

    @Autowired
    void add(ActionRouter actionRouter) {
        actionRouter.put(getKey(), this);
    }

    public ReplyKeyboard keyboard() {
        return new ReplyKeyboardRemove(true);
    }

}