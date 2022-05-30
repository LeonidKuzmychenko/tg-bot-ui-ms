package tg.project.ui.bot.actions.commands;

import tg.project.ui.bot.Action;
import tg.project.ui.bot.constants.action.ActionCommands;
import tg.project.ui.bot.model.BotUser;
import tg.project.ui.bot.services.BotUserService;
import tg.project.ui.bot.services.KeyboardProvider;
import tg.project.ui.bot.services.MessageProvider;
import tg.project.ui.bot.services.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ShowSubscribesAction extends Action {

    private String[] serials = new String[]{
            "• Сверхъестественное",
            "• Друзья",
            "• Большой куш",
            "• Ганнибал",
            "• Волчонок"
    };

    protected ShowSubscribesAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        sendMessage(messageProvider.getShowAllSubscribesMessage(chatId, serials));
        setCommand(chatId, null);
    }

    @Override
    public String getKey() {
        return ActionCommands.SHOW_SUBSCRIBES;
    }
}