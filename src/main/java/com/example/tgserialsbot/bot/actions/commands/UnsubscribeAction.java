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

import java.util.Arrays;

@Component
public class UnsubscribeAction extends Action {

    private String[] serialsId = new String[]{
            "14567",
            "14598",
            "15642",
            "16989",
            "17102",
            "Отмена"
    };

    private String[] serials = new String[]{
            "• Сверхъестественное (14567)",
            "• Друзья (14598)",
            "• Большой куш (15642)",
            "• Ганнибал (16989)",
            "• Волчонок (17102)"
    };

    protected UnsubscribeAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        messageSender.send(messageProvider.getListOfAllSerialsWithIdMessage(chatId, serials));
        messageSender.send(messageProvider.getSendIdSerialForUnsubscribeMessage(chatId, transformSerialIds()));
    }

    private String[][] transformSerialIds() {
        int size = 5;
        if (serialsId.length > size) {
            int scale = (int) Math.ceil(serialsId.length / (double) size);
            String[][] newSerialId = new String[scale][size];
            for (int i = 0; i < serialsId.length; i++) {
                newSerialId[i / size][i % size] = serialsId[i];
            }
            System.out.println(Arrays.deepToString(newSerialId));
            return newSerialId;
        }
        return new String[][]{serialsId};
    }

    @Override
    public String getKey() {
        return ActionCommands.UNSUBSCRIBE;
    }
}