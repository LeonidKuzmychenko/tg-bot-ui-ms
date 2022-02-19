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
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShowSerialForApproveAction extends Action {
    public ShowSerialForApproveAction(KeyboardProvider keyboardProvider, AbsSender absSender, BotUserService botUserService) {
        super(keyboardProvider, absSender, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.NEEDED_SERIAL));
        sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        send(sendMessage);

        InputFile photoFile = new InputFile(new File("src/main/resources/test/photo.png"));
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(photoFile);
        sendPhoto.setChatId(chatId);
        sendPhoto.setReplyMarkup(keyboard());
        send(sendPhoto);

        botUserService.setCommand(chatId, ActionAnswers.ANSWER_SERIAL_FOR_APPROVE);
    }

    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(variables.get(ActionMenu.YES_THIS));
        keyboardRow.add(variables.get(ActionMenu.NO_SHOW_NEXT));
        keyboardRow.add(variables.get(ActionMenu.CLOSE));

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    @Override
    public String getKey() {
        return ActionAnswers.SHOW_SERIAL_FOR_APPROVE;
    }
}
