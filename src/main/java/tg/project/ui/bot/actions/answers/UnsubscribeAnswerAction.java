package tg.project.ui.bot.actions.answers;

import tg.project.ui.bot.Action;
import tg.project.ui.bot.constants.action.ActionAnswers;
import tg.project.ui.bot.model.BotUser;
import tg.project.ui.bot.services.BotUserService;
import tg.project.ui.bot.services.KeyboardProvider;
import tg.project.ui.bot.services.MessageProvider;
import tg.project.ui.bot.services.MessageSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class UnsubscribeAnswerAction extends Action {
    protected UnsubscribeAnswerAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    protected void action(Update update, BotUser botUser, String chatId, String text) {
        if (StringUtils.hasLength(text) && text.chars().allMatch(Character::isDigit)) {
            sendMessage(messageProvider.getAnswerForUnsubscribeMessage(chatId));
            setCommand(chatId, null);
            return;
        }
        sendMessage(messageProvider.getChooseVariantFromOptionsMessage(chatId));
    }

    @Override
    public String getKey() {
        return ActionAnswers.ANSWER_UNSUBSCRIBE;
    }
}
