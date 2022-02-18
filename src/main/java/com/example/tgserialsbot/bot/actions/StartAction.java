package com.example.tgserialsbot.bot.actions;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import static com.example.tgserialsbot.bot.ActionCommands.START;
import static com.example.tgserialsbot.bot.ActionCommands.STEP_SERIAL_NAME_SUBSCRIBE_ACTION;

@Component
public class StartAction extends Action {

    public StartAction(BotUserService botUserService) {
        super(botUserService);
    }

    @Override
    public void action(Update update, AbsSender absSender, BotUser botUser, String chatId, String text) {
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setChatId(chatId);
        sendMessage1.setText("Привет! В этом боте ты можешь подписаться на сериалы, которые тебе нравятся и бот тебя уведомит о выходе новых серий.");
        sendMessage1.setReplyMarkup(defaultKeyboard());
        send(absSender, sendMessage1);

        SendMessage sendMessage2 = new SendMessage();
        sendMessage2.setChatId(chatId);
        sendMessage2.setText("Начни прямо сейчас. Напиши название сериала, на который хочешь подписаться:");
        sendMessage2.setReplyMarkup(defaultKeyboard());
        send(absSender, sendMessage2);

        botUserService.setCommand(chatId, STEP_SERIAL_NAME_SUBSCRIBE_ACTION);
    }

//    public ReplyKeyboard keyboard() {
//        KeyboardRow keyboardRow = new KeyboardRow();
//        keyboardRow.add("Мои записи");
//
//        List<KeyboardRow> keyboardRows = new ArrayList<>();
//        keyboardRows.add(keyboardRow);
//
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        replyKeyboardMarkup.setKeyboard(keyboardRows);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        return replyKeyboardMarkup;
//    }

    @Override
    public String getKey() {
        return START;
    }
}