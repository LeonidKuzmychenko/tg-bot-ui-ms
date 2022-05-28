package home.project.tgserialsbot.bot.actions.commands;

import home.project.tgserialsbot.bot.Action;
import home.project.tgserialsbot.bot.constants.action.ActionCommands;
import home.project.tgserialsbot.bot.model.BotUser;
import home.project.tgserialsbot.bot.services.BotUserService;
import home.project.tgserialsbot.bot.services.KeyboardProvider;
import home.project.tgserialsbot.bot.services.MessageProvider;
import home.project.tgserialsbot.bot.services.MessageSender;
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