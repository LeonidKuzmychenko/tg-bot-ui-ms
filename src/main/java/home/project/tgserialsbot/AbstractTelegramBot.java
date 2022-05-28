package home.project.tgserialsbot;

import home.project.tgserialsbot.bot.ActionRouter;
import home.project.tgserialsbot.bot.services.BotUserService;
import home.project.tgserialsbot.properties.TelegramProperty;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractTelegramBot extends TelegramLongPollingBot {

    public final ActionRouter router;
    public final BotUserService botUserService;
    public final TelegramProperty property;

    public AbstractTelegramBot(ActionRouter router, BotUserService botUserService, TelegramProperty property) {
        this.router = router;
        this.botUserService = botUserService;
        this.property = property;
    }

    @Override
    public String getBotUsername() {
        return property.getName();
    }

    @Override
    public String getBotToken() {
        return property.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            final Message message = update.getMessage();
            final String chatId = message.getChatId().toString();
            if (message.hasText()) {
                String text = message.getText();
                messageWithText(update, message, chatId, text);
            } else {
                messageWithOutText(update, message, chatId);
            }
        }
        if (update.hasCallbackQuery()) {
            callback(update);
        }
    }

    public abstract void messageWithText(Update update, Message message, String chatId, String text);

    public abstract void messageWithOutText(Update update, Message message, String chatId);

    public abstract void callback(Update update);
}