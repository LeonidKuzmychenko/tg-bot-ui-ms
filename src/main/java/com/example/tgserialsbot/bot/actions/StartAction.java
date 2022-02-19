package com.example.tgserialsbot.bot.actions;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.constants.ActionAnswers;
import com.example.tgserialsbot.bot.constants.ActionMessages;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import static com.example.tgserialsbot.bot.constants.ActionCommands.START;

@Component
public class StartAction extends Action {

    public StartAction(KeyboardProvider keyboardProvider, AbsSender absSender, BotUserService botUserService) {
        super(keyboardProvider, absSender, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setChatId(chatId);
        sendMessage1.setText(messages.get(ActionMessages.HELLO));
        sendMessage1.setReplyMarkup(keyboardProvider.emptyKeyboard());
        send(sendMessage1);

        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setChatId(chatId);
        sendMessage2.setText(messages.get(ActionMessages.HELLO_INPUT));
        sendMessage2.setReplyMarkup(keyboardProvider.emptyKeyboard());
        send(sendMessage2);

        botUserService.setCommand(chatId, ActionAnswers.SHOW_SERIAL_FOR_APPROVE);
    }

    @Override
    public String getKey() {
        return START;
    }
}