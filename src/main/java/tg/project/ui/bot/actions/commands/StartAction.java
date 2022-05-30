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
public class StartAction extends Action {

    protected StartAction(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        super(messageSender, messageProvider, keyboardProvider, botUserService);
    }

    @Override
    public void action(Update update, BotUser botUser, String chatId, String text) {
        sendMessage(messageProvider.getHelloMessage(chatId));
        sendMessage(messageProvider.getHelloInputMessage(chatId));
        setCommand(chatId, ActionAnswers.SHOW_SERIAL_FOR_APPROVE);

//        List<BotCommand> botCommandList = new ArrayList<>();
//        botCommandList.add(new BotCommand("/subscribe","подписаться на новый сериал"));
//        botCommandList.add(new BotCommand("/unsubscribe","отписаться от одного сериала"));
//        botCommandList.add(new BotCommand("/showsubscribes","отобразить список всех подписок"));
//        botCommandList.add(new BotCommand("/unsubscribeall","отписаться от всех сериалов"));
//        SetMyCommands setMyCommands = new SetMyCommands(botCommandList, new BotCommandScopeDefault(),"ru");
//        sendMessage(setMyCommands);
    }

    @Override
    public String getKey() {
        return ActionCommands.START;
    }
}