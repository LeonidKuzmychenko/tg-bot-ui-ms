package com.example.tgserialsbot.bot.actions.answers;

import com.example.tgserialsbot.bot.Action;
import com.example.tgserialsbot.bot.constants.action.ActionAnswers;
import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.bot.services.BotUserService;
import com.example.tgserialsbot.bot.services.KeyboardProvider;
import com.example.tgserialsbot.bot.services.MessageProvider;
import com.example.tgserialsbot.bot.services.MessageSender;
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
