package com.example.tgserialsbot.bot.actions;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import static com.example.tgserialsbot.bot.ActionCommands.STEP_SERIAL_NAME_SUBSCRIBE_ACTION;

@Component
public class WaitSerialNameSubscribeAction extends Action {

    public WaitSerialNameSubscribeAction(BotUserService botUserService) {
        super(botUserService);
    }

    @Override
    public void action(Update update, AbsSender absSender, BotUser botUser, String chatId, String text) {
        Message message = update.getMessage();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Круто! Подписка на сериал '" + text + "' была совершена.");
        sendMessage.setReplyMarkup(defaultKeyboard());
        send(absSender, sendMessage);

        botUserService.setCommand(chatId, null);
    }

    @Override
    public String getKey() {
        return STEP_SERIAL_NAME_SUBSCRIBE_ACTION;//TODO
    }
}