package com.example.tgserialsbot.bot.actions;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.constants.ActionAnswers;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class SubscribeAction extends Action {

    public SubscribeAction(KeyboardProvider keyboardProvider, AbsSender absSender, BotUserService botUserService) {
        super(keyboardProvider, absSender, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {

    }

    @Override
    public String getKey() {
        return ActionAnswers.SUBSCRIBE_ACTION;
    }
}