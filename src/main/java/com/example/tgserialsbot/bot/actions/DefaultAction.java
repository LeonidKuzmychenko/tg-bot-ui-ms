package com.example.tgserialsbot.bot.actions;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.ActionCommands;
import com.example.tgserialsbot.bot.services.BotUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class DefaultAction extends Action {

    public DefaultAction(BotUserService botUserService) {
        super(botUserService);
    }

    @Override
    public void action(Update update, AbsSender absSender) {

    }

    @Override
    public String getKey() {
        return ActionCommands.ANY;
    }
}