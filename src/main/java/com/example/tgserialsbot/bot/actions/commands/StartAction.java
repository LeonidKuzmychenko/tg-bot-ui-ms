package com.example.tgserialsbot.bot.actions.commands;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.constants.action.ActionAnswers;
import com.example.tgserialsbot.bot.constants.action.ActionCommands;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import com.example.tgserialsbot.bot.services.MessageProvider;
import com.example.tgserialsbot.bot.services.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartAction extends Action {

    protected StartAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        messageSender.send(messageProvider.getHelloMessage(chatId));
        messageSender.send(messageProvider.getHelloInputMessage(chatId));
        botUserService.setCommand(chatId, ActionAnswers.SHOW_SERIAL_FOR_APPROVE);
    }

    @Override
    public String getKey() {
        return ActionCommands.START;
    }
}