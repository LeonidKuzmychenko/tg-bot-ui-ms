package com.example.tgserialsbot.bot.actions;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.constants.ActionCommands;
import com.example.tgserialsbot.bot.constants.ActionMessages;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class MenuAction extends Action {
    public MenuAction(KeyboardProvider keyboardProvider, AbsSender absSender, BotUserService botUserService) {
        super(keyboardProvider, absSender, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.SHOW_MENU));
        sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        send(sendMessage);
    }

    @Override
    public String getKey() {
        return ActionCommands.MENU;
    }
}
