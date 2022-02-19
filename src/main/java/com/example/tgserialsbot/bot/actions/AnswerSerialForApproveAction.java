package com.example.tgserialsbot.bot.actions;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.constants.ActionAnswers;
import com.example.tgserialsbot.bot.constants.ActionMenu;
import com.example.tgserialsbot.bot.constants.ActionMessages;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class AnswerSerialForApproveAction extends Action {

    public AnswerSerialForApproveAction(KeyboardProvider keyboardProvider, AbsSender absSender, BotUserService botUserService) {
        super(keyboardProvider, absSender, botUserService);
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
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setChatId(chatId);
        sendMessage1.setText(messages.get(ActionMessages.HAS_BEEN_SUBSCRIBE));
        sendMessage1.setReplyMarkup(keyboardProvider.emptyKeyboard());
        send(sendMessage1);

        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setChatId(chatId);
        sendMessage2.setText(messages.get(ActionMessages.SHOW_MENU));
        sendMessage2.setReplyMarkup(keyboardProvider.emptyKeyboard());
        send(sendMessage2);

        botUserService.setCommand(chatId, null);
    }

    private void next(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Этот функционгал еще не готов");
        sendMessage.setReplyMarkup(keyboardProvider.keyboardApproveSerial());
        send(sendMessage);
    }

    private void close(String chatId) {
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setChatId(chatId);
        sendMessage1.setText(messages.get(ActionMessages.SERIAL_NOT_FOUND));
        sendMessage1.setReplyMarkup(keyboardProvider.emptyKeyboard());
        send(sendMessage1);

        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setChatId(chatId);
        sendMessage2.setText(messages.get(ActionMessages.SHOW_MENU));
        sendMessage2.setReplyMarkup(keyboardProvider.emptyKeyboard());
        send(sendMessage2);

        botUserService.setCommand(chatId, null);
    }

    private void error(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.CHOOSE_A_VARIANT_FROM_OPTIONS));
        sendMessage.setReplyMarkup(keyboardProvider.keyboardApproveSerial());
        send(sendMessage);
    }

    @Override
    public String getKey() {
        return ActionAnswers.ANSWER_SERIAL_FOR_APPROVE;
    }
}