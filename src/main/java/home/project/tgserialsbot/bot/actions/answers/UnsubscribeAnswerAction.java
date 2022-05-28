package home.project.tgserialsbot.bot.actions.answers;

import home.project.tgserialsbot.bot.Action;
import home.project.tgserialsbot.bot.constants.action.ActionAnswers;
import home.project.tgserialsbot.bot.model.BotUser;
import home.project.tgserialsbot.bot.services.BotUserService;
import home.project.tgserialsbot.bot.services.KeyboardProvider;
import home.project.tgserialsbot.bot.services.MessageProvider;
import home.project.tgserialsbot.bot.services.MessageSender;
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
