package tg.project.ui.bot.actions.commands;

import tg.project.ui.bot.Action;
import tg.project.ui.bot.constants.action.ActionAnswers;
import tg.project.ui.bot.constants.action.ActionCommands;
import tg.project.ui.bot.model.BotUser;
import tg.project.ui.bot.services.BotUserService;
import tg.project.ui.bot.services.KeyboardProvider;
import tg.project.ui.bot.services.MessageProvider;
import tg.project.ui.bot.services.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class SubscribeAction extends Action {

    protected SubscribeAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        sendMessage(messageProvider.getSubscribeMessage(chatId));
        setCommand(chatId, ActionAnswers.SHOW_SERIAL_FOR_APPROVE);
    }

    @Override
    public String getKey() {
        return ActionCommands.SUBSCRIBE;
    }
}