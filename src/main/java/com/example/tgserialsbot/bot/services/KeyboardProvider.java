package com.example.tgserialsbot.bot.services;

import com.example.tgserialsbot.bot.constants.ActionMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class KeyboardProvider {

    @Autowired
    @Qualifier("tgMessages")
    public Map<String, String> messages;

    @Autowired
    @Qualifier("tgAnswerVariables")
    public Map<String, String> variables;


    public ReplyKeyboard keyboardApproveSerial() {
        return new KeyboardProvider().variablesKeyboard(
                variables.get(ActionMenu.YES_THIS),
                variables.get(ActionMenu.NO_SHOW_NEXT),
                variables.get(ActionMenu.CLOSE)
        );
    }

    public ReplyKeyboard emptyKeyboard() {
        return null;
    }

    private ReplyKeyboard variablesKeyboard(String... variables) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(Arrays.asList(variables));

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboard emptyKeyboard3() {
        return null;
    }

    public ReplyKeyboard emptyKeyboard4() {
        return null;
    }
}
