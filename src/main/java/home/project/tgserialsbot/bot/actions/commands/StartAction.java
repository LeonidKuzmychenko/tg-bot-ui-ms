package home.project.tgserialsbot.bot.actions.commands;

import home.project.tgserialsbot.bot.Action;
import home.project.tgserialsbot.bot.constants.action.ActionAnswers;
import home.project.tgserialsbot.bot.constants.action.ActionCommands;
import home.project.tgserialsbot.bot.model.BotUser;
import home.project.tgserialsbot.bot.services.BotUserService;
import home.project.tgserialsbot.bot.services.KeyboardProvider;
import home.project.tgserialsbot.bot.services.MessageProvider;
import home.project.tgserialsbot.bot.services.MessageSender;
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