package tg.project.ui.bot.actions.answers;

import tg.project.ui.bot.Action;
import tg.project.ui.bot.constants.action.ActionAnswers;
import tg.project.ui.bot.constants.mapkey.ActionMenu;
import tg.project.ui.bot.model.BotUser;
import tg.project.ui.bot.services.BotUserService;
import tg.project.ui.bot.services.KeyboardProvider;
import tg.project.ui.bot.services.MessageProvider;
import tg.project.ui.bot.services.MessageSender;
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
