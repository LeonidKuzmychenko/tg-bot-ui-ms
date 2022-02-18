package com.example.tgserialsbot;

import com.example.tgserialsbot.bot.ActionRouter;
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
        BotUser botUser = botUserService.getUserWithCreate(chatId);

        String command = botUser.getCommand();
        if (command == null) {
            command = text;
        }

        System.out.println();
        System.out.println(command);
        System.out.println();

        router.get(command).action(update, this, botUser, chatId, text);
    }

    @Override
    public void messageWithOutText(Update update, Message message, String chatId) {

    }

    @Override
    public void callback(Update update) {

    }
}