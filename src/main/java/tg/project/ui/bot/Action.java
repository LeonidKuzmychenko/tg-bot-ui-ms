package tg.project.ui.bot;

import tg.project.ui.bot.model.BotUser;
import tg.project.ui.bot.services.BotUserService;
import tg.project.ui.bot.services.KeyboardProvider;
import tg.project.ui.bot.services.MessageProvider;
import tg.project.ui.bot.services.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Slf4j
public abstract class Action {

    @Autowired
    @Qualifier("tgMessages")
    public Map<String, String> messages;

    @Autowired
    @Qualifier("tgAnswerVariables")
    public Map<String, String> variables;

    public final MessageProvider messageProvider;
    public final KeyboardProvider keyboardProvider;

    private final MessageSender messageSender;
    private final BotUserService botUserService;

    protected Action(MessageSender messageSender, MessageProvider messageProvider, KeyboardProvider keyboardProvider, BotUserService botUserService) {
        this.messageSender = messageSender;
        this.messageProvider = messageProvider;
        this.keyboardProvider = keyboardProvider;
        this.botUserService = botUserService;
    }

    public void run(Update update, BotUser botUser, String chatId, String text) {
        log.info("__________________________________________");
        log.info("Start action '{}'", getClass().getSimpleName());
        log.info("With command = '{}'", botUser.getCommand());
        log.info("With message = '{}'", text);
        action(update, botUser, chatId, text);
        log.info("End action '{}'", getClass().getSimpleName());
    }

    public void sendMessage(SendMessage sendMessage) {
        messageSender.send(sendMessage);
    }

    public void sendMessage(SendPhoto sendPhoto) {
        messageSender.send(sendPhoto);
    }

    public void sendMessage(SetMyCommands commands) {
        messageSender.send(commands);
    }

    public void setCommand(String chatId, String command) {
        botUserService.setCommand(chatId, command);
    }

    protected abstract void action(Update update, BotUser botUser, String chatId, String text);

    public abstract String getKey();

    @Autowired
    void add(ActionRouter actionRouter) {
        actionRouter.put(getKey(), this);
    }
}