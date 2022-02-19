package com.example.tgserialsbot.bot.actions.answers;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.constants.action.ActionAnswers;
import com.example.tgserialsbot.bot.constants.mapkey.ActionMenu;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import com.example.tgserialsbot.bot.services.MessageProvider;
import com.example.tgserialsbot.bot.services.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class AnswerSerialForApproveAction extends Action {

    protected AnswerSerialForApproveAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        if (variables.get(ActionMenu.YES_THIS).equals(text)) {
            approve(chatId);
            return;
        }
        if (variables.get(ActionMenu.NO_SHOW_NEXT).equals(text)) {
            next(chatId);
            return;
        }
        if (variables.get(ActionMenu.CLOSE).equals(text)) {
            close(chatId);
            return;
        }
        error(chatId);
    }

    private void approve(String chatId) {
        messageSender.send(messageProvider.getHasBeenSubscribeMessage(chatId));
        botUserService.setCommand(chatId, null);
    }

    private void next(String chatId) {
        messageSender.send(messageProvider.getErrorMessage(chatId));
    }

    private void close(String chatId) {
        messageSender.send(messageProvider.getSerialNotFoundMessage(chatId));
        botUserService.setCommand(chatId, null);
    }

    private void error(String chatId) {
        messageSender.send(messageProvider.getChooseVariantFromOptionsMessage(chatId));
    }

    @Override
    public String getKey() {
        return ActionAnswers.ANSWER_SERIAL_FOR_APPROVE;
    }
}