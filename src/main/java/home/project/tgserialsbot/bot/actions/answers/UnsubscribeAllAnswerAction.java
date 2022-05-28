package home.project.tgserialsbot.bot.actions.answers;

import home.project.tgserialsbot.bot.Action;
import home.project.tgserialsbot.bot.constants.action.ActionAnswers;
import home.project.tgserialsbot.bot.constants.mapkey.ActionMenu;
import home.project.tgserialsbot.bot.model.BotUser;
import home.project.tgserialsbot.bot.services.BotUserService;
import home.project.tgserialsbot.bot.services.KeyboardProvider;
import home.project.tgserialsbot.bot.services.MessageProvider;
import home.project.tgserialsbot.bot.services.MessageSender;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class UnsubscribeAllAnswerAction extends Action {
    protected UnsubscribeAllAnswerAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    protected void action(Update update, BotUser botUser, String chatId, String text) {
        if (ActionMenu.YES.equals(text)) {
            sendMessage(messageProvider.getAnswerForUnsubscribeAllMessage(chatId));
            setCommand(chatId, null);
            return;
        }
        if (ActionMenu.NO.equals(text)) {
            sendMessage(messageProvider.getUnsubscribeFromAllSerialsHasBeenCloseMessage(chatId));
            setCommand(chatId, null);
            return;
        }
        sendMessage(messageProvider.getChooseVariantFromOptionsMessage(chatId));
    }

    @Override
    public String getKey() {
        return ActionAnswers.ANSWER_UNSUBSCRIBE_ALL;
    }
}
