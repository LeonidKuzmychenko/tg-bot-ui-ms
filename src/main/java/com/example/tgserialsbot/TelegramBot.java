package com.example.tgserialsbot;

import com.example.tgserialsbot.bot.ActionRouter;
import com.example.tgserialsbot.bot.constants.action.ActionCommands;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.properties.TelegramProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class TelegramBot extends AbstractTelegramBot {

    public TelegramBot(ActionRouter router, BotUserService botUserService, TelegramProperty property) {
        super(router, botUserService, property);
    }

    @Override
    public void messageWithText(Update update, Message message, String chatId, String text) {
        BotUser botUser = botUserService.getUser(chatId);

        if (ActionCommands.LIST.contains(text)) {
            router.get(text).run(update, botUser, chatId, text);
            return;
        }

        String command = botUser.getCommand();
        if (command == null) {
            command = text;
        }
        router.get(command).run(update, botUser, chatId, text);

    }

    @Override
    public void messageWithOutText(Update update, Message message, String chatId) {

    }

    @Override
    public void callback(Update update) {

    }
}