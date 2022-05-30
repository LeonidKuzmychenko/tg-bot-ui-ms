package tg.project.ui.bot.actions.answers;

import tg.project.ui.bot.Action;
import tg.project.ui.bot.constants.action.ActionAnswers;
import tg.project.ui.bot.constants.mapkey.ActionMenu;
import tg.project.ui.bot.model.BotUser;
import tg.project.ui.bot.services.BotUserService;
import tg.project.ui.bot.services.KeyboardProvider;
import tg.project.ui.bot.services.MessageProvider;
import tg.project.ui.bot.services.MessageSender;
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