package com.example.tgserialsbot.bot.services;

import com.example.tgserialsbot.bot.constants.mapkey.ActionMessages;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class MessageProvider {

    public final Map<String, String> messages;
    public final Map<String, String> variables;
    public final KeyboardProvider keyboardProvider;

    public MessageProvider(@Qualifier("tgMessages") Map<String, String> messages,
                           @Qualifier("tgAnswerVariables") Map<String, String> variables,
                           KeyboardProvider keyboardProvider) {
        this.messages = messages;
        this.variables = variables;
        this.keyboardProvider = keyboardProvider;
    }

    public SendMessage getUnsubscribeFromAllSerialsHasBeenCloseMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.UNSUBSCRIBE_FROM_ALL_SERIAL_HAS_BEEN_CLOSE));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        });
    }

    public SendMessage getAnswerForUnsubscribeMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.ANSWER_FOR_UNSUBSCRIBE_SERIAL));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        });
    }

    public SendMessage getAnswerForUnsubscribeAllMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.ANSWER_FOR_UNSUBSCRIBE_ALL));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        });
    }

    public SendMessage getShowAllSubscribesMessage(String chatId, String[] serials) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.LIST_OF_ALL_SERIALS) + String.join("\n", serials));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        });
    }

    public SendMessage getSubscribeMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.SUBSCRIBE));
            sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        });
    }

    public SendMessage getSendIdSerialForUnsubscribeMessage(String chatId, String[][] serialsId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.SEND_ID_SERIAL_FOR_UNSUBSCRIBE));
            sendMessage.setReplyMarkup(keyboardProvider.variablesManyRowsKeyboard(serialsId));
        });
    }

    public SendMessage getListOfAllSerialsWithIdMessage(String chatId, String[] serials) {
        return getSendMessage(chatId, sendMessage ->
                sendMessage.setText(messages.get(ActionMessages.LIST_OF_ALL_SERIALS) + String.join("\n", serials)));
    }

    public SendMessage getUnsubscribeAllMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.QUESTION_UNSUBSCRIBE_ALL));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardUnsubscribeAll());
        });
    }

    public SendMessage getItIsNeededSerialMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.NEEDED_SERIAL));
            sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        });
    }

    public SendMessage getErrorMessage(String chatId) {
        return getSendMessage(chatId, sendMessage ->
                sendMessage.setText("Ошибочка вышла\nВозможно функционал еще не готов \uD83D\uDE2D"));
    }

    public SendMessage getHasBeenSubscribeMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.HAS_BEEN_SUBSCRIBE));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        });
    }

    public SendMessage getChooseVariantFromOptionsMessage(String chatId) {
        return getSendMessage(chatId, sendMessage ->
                sendMessage.setText(messages.get(ActionMessages.CHOOSE_A_VARIANT_FROM_OPTIONS)));
    }

    public SendMessage getSerialNotFoundMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.SERIAL_NOT_FOUND));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        });
    }

    //TODO посмотреть как можно изменнить клавиатуру без отправки сообщения в чат
    public SendMessage getMenuMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.SHOW_MENU));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        });
    }

    public SendMessage getHelloMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.HELLO));
            sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        });
    }

    public SendMessage getHelloInputMessage(String chatId) {
        return getSendMessage(chatId, sendMessage -> {
            sendMessage.setText(messages.get(ActionMessages.HELLO_INPUT));
            sendMessage.setReplyMarkup(keyboardProvider.keyboardMenu());
        });
    }

    public SendPhoto getSerialPosterMessage(String chatId) {
        InputFile photoFile = new InputFile(new File("src/main/resources/test/photo.png"));
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(photoFile);
        sendPhoto.setChatId(chatId);
        sendPhoto.setReplyMarkup(keyboardProvider.keyboardApproveSerial());
        return sendPhoto;
    }

    private SendMessage getSendMessage(String chatId, Consumer<SendMessage> consumer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        consumer.accept(sendMessage);
        return sendMessage;
    }
}