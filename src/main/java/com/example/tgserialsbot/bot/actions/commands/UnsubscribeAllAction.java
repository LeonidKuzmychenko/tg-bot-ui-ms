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
public class UnsubscribeAllAction extends Action {

    protected UnsubscribeAllAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        sendMessage(messageProvider.getUnsubscribeAllMessage(chatId));
        setCommand(chatId, ActionAnswers.ANSWER_UNSUBSCRIBE_ALL);
    }

    @Override
    public String getKey() {
        return ActionCommands.UNSUBSCRIBE_ALL;
    }

}