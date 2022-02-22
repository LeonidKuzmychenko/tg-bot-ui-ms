package com.example.tgserialsbot.bot.actions.answers;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.constants.action.ActionAnswers;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import com.example.tgserialsbot.bot.services.MessageProvider;
import com.example.tgserialsbot.bot.services.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ShowSerialForApproveAction extends Action {

    protected ShowSerialForApproveAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        sendMessage(messageProvider.getItIsNeededSerialMessage(chatId));
        sendMessage(messageProvider.getSerialPosterMessage(chatId));
        setCommand(chatId, ActionAnswers.ANSWER_SERIAL_FOR_APPROVE);
    }

    @Override
    public String getKey() {
        return ActionAnswers.SHOW_SERIAL_FOR_APPROVE;
    }
}