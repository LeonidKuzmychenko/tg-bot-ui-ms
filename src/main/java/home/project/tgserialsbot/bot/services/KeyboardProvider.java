package home.project.tgserialsbot.bot.services;

import home.project.tgserialsbot.bot.constants.action.ActionCommands;
import home.project.tgserialsbot.bot.constants.mapkey.ActionMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class KeyboardProvider {

    @Autowired
    @Qualifier("tgMessages")
    public Map<String, String> messages;

    @Autowired
    @Qualifier("tgAnswerVariables")
    public Map<String, String> variables;

    public ReplyKeyboard keyboardUnsubscribeAll() {
        return new KeyboardProvider().variablesKeyboard(
                ActionMenu.YES,
                ActionMenu.NO
        );
    }

    public ReplyKeyboard keyboardMenu() {
        return new KeyboardProvider().variablesKeyboard(
                ActionCommands.MENU
        );
    }

    public ReplyKeyboard keyboardFullMenu() {
        return new KeyboardProvider().variablesManyRowsKeyboard(
                new String[][]{
                        {ActionCommands.SUBSCRIBE, ActionCommands.UNSUBSCRIBE},
                        {ActionCommands.SHOW_SUBSCRIBES, ActionCommands.UNSUBSCRIBE_ALL},
                        {ActionCommands.MENU}
                }
        );
    }

    public ReplyKeyboard keyboardApproveSerial() {
        return new KeyboardProvider().variablesManyRowsKeyboard(
                new String[][]{
                        {variables.get(ActionMenu.YES_THIS), variables.get(ActionMenu.NO_SHOW_NEXT)},
                        {variables.get(ActionMenu.CLOSE)}
                }
        );
    }

    public ReplyKeyboard emptyKeyboard() {
        return new ReplyKeyboardRemove(true);
    }

    public ReplyKeyboard variablesKeyboard(String... variables) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(Arrays.asList(variables));
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboard variablesManyRowsKeyboard(String[]... variables) {
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        for (String[] vars : variables) {
            List<String> collect = Arrays.stream(vars)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.addAll(collect);
            keyboardRows.add(keyboardRow);
        }
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

}
