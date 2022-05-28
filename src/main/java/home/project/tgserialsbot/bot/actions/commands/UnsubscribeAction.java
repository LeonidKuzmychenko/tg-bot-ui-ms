package home.project.tgserialsbot.bot.actions.commands;

import home.project.tgserialsbot.bot.Action;
import home.project.tgserialsbot.bot.constants.action.ActionAnswers;
import home.project.tgserialsbot.bot.constants.action.ActionCommands;
import home.project.tgserialsbot.bot.model.BotUser;
import home.project.tgserialsbot.bot.services.BotUserService;
import home.project.tgserialsbot.bot.services.KeyboardProvider;
import home.project.tgserialsbot.bot.services.MessageProvider;
import home.project.tgserialsbot.bot.services.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UnsubscribeAction extends Action {

    private String[] serialsId = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "Отмена"
    };

    private String[] serials = new String[]{
            "1. Сверхъестественное",
            "2. Друзья",
            "3. Большой куш",
            "4. Ганнибал",
            "5. Волчонок",
            "6. Сверхъестественное",
            "7. Друзья",
            "8. Большой куш",
            "9. Ганнибал",
            "10. Волчонок"
    };

    protected UnsubscribeAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        sendMessage(messageProvider.getListOfAllSerialsWithIdMessage(chatId, serials));
        sendMessage(messageProvider.getSendIdSerialForUnsubscribeMessage(chatId, transformSerialIds()));
        setCommand(chatId, ActionAnswers.ANSWER_UNSUBSCRIBE);
    }

    private String[][] transformSerialIds() {
        int size = 5;
        if (serialsId.length > size) {
            int scale = (int) Math.ceil(serialsId.length / (double) size);
            String[][] newSerialId = new String[scale][size];
            for (int i = 0; i < serialsId.length; i++) {
                newSerialId[i / size][i % size] = serialsId[i];
            }
            return newSerialId;
        }
        return new String[][]{serialsId};
    }

    @Override
    public String getKey() {
        return ActionCommands.UNSUBSCRIBE;
    }
}