package tg.project.ui;

import tg.project.ui.bot.ActionRouter;
import tg.project.ui.bot.constants.action.ActionCommands;
import tg.project.ui.bot.model.BotUser;
import tg.project.ui.bot.services.BotUserService;
import tg.project.ui.properties.TelegramProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class TelegramBot extends AbstractTelegramBot {

    public TelegramBot(ActionRouter router, BotUserService botUserService, TelegramProperty property) {
        super(router, botUserService, property);
    }

    @Override
    public void messageWithText(Update update, Message message, String chatId, String text) {
        BotUser botUser = botUserService.getUser(chatId);

        if (ActionCommands.LIST.contains(text)) {
            router.get(text).run(update, botUser, chatId, text);
            return;
        }

        String command = botUser.getCommand();
        if (command == null) {
            command = text;
        }
        router.get(command).run(update, botUser, chatId, text);

    }

    @Override
    public void messageWithOutText(Update update, Message message, String chatId) {

    }

    @Override
    public void callback(Update update) {

    }
}