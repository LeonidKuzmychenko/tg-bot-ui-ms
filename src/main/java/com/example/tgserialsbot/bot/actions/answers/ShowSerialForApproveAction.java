package com.example.tgserialsbot.bot.actions.answers;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.constants.action.ActionAnswers;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.*;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ShowSerialForApproveAction extends Action {

    private final SearchSerialService searchSerialService;

    protected ShowSerialForApproveAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService, SearchSerialService searchSerialService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
        this.searchSerialService = searchSerialService;
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        String serialInfo = searchSerialService.getSerialInfo(text);
        sendMessage(messageProvider.getItIsNeededSerialMessage(chatId));
        sendMessage(messageProvider.getSerialInfoMessage(chatId, serialInfo));
        setCommand(chatId, ActionAnswers.ANSWER_SERIAL_FOR_APPROVE);
    }

    @Override
    public String getKey() {
        return ActionAnswers.SHOW_SERIAL_FOR_APPROVE;
    }
}