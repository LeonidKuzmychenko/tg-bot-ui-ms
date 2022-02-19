package com.example.tgserialsbot.bot.actions.commands;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.constants.action.ActionCommands;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import com.example.tgserialsbot.bot.services.MessageProvider;
import com.example.tgserialsbot.bot.services.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ShowSubscribesAction extends Action {

    private String[] serials = new String[]{
            "• Сверхъестественное",
            "• Друзья",
            "• Волчонок"
    };

    protected ShowSubscribesAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        messageSender.send(messageProvider.getShowAllSubscribesMessage(chatId, serials));
    }

    @Override
    public String getKey() {
        return ActionCommands.SHOW_SUBSCRIBES;
    }
}