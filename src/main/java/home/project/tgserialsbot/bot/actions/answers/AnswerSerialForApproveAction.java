package home.project.tgserialsbot.bot.actions.answers;

import home.project.tgserialsbot.bot.Action;
import home.project.tgserialsbot.bot.constants.action.ActionAnswers;
import home.project.tgserialsbot.bot.constants.mapkey.ActionMenu;
import home.project.tgserialsbot.bot.model.BotUser;
import home.project.tgserialsbot.bot.services.BotUserService;
import home.project.tgserialsbot.bot.services.KeyboardProvider;
import home.project.tgserialsbot.bot.services.MessageProvider;
import home.project.tgserialsbot.bot.services.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class AnswerSerialForApproveAction extends Action {

    protected AnswerSerialForApproveAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        if (variables.get(ActionMenu.YES_THIS).equals(text)) {
            approve(chatId);
            return;
        }
        if (variables.get(ActionMenu.NO_SHOW_NEXT).equals(text)) {
            next(chatId);
            return;
        }
        if (variables.get(ActionMenu.CLOSE).equals(text)) {
            close(chatId);
            return;
        }
        error(chatId);
    }

    private void approve(String chatId) {
        sendMessage(messageProvider.getHasBeenSubscribeMessage(chatId));
        setCommand(chatId, null);
    }

    private void next(String chatId) {
        sendMessage(messageProvider.getErrorMessage(chatId));
    }

    private void close(String chatId) {
        sendMessage(messageProvider.getSerialNotFoundMessage(chatId));
        setCommand(chatId, null);
    }

    private void error(String chatId) {
        sendMessage(messageProvider.getChooseVariantFromOptionsMessage(chatId));
    }

    @Override
    public String getKey() {
        return ActionAnswers.ANSWER_SERIAL_FOR_APPROVE;
    }
}