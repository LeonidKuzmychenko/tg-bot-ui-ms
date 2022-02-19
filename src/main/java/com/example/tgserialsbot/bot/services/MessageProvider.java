package com.example.tgserialsbot.bot.services;

import com.example.tgserialsbot.bot.constants.mapkey.ActionMessages;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
import java.util.Map;

@Service
public class MessageProvider {

    public final Map<String, String> messages;
    public final Map<String, String> variables;
    public final KeyboardProvider keyboardProvider;

    public MessageProvider(@Qualifier("tgMessages") Map<String, String> messages, @Qualifier("tgAnswerVariables") Map<String, String> variables, KeyboardProvider keyboardProvider) {
        this.messages = messages;
        this.variables = variables;
        this.keyboardProvider = keyboardProvider;
    }

    public SendMessage getShowAllSubscribesMessage(String chatId, String[] serials) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.LIST_OF_ALL_SERIALS) + String.join("\n", serials));
        sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        return sendMessage;
    }

    public SendMessage getSubscribeMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.SUBSCRIBE));
        sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        return sendMessage;
    }

    public SendMessage getSendIdSerialForUnsubscribeMessage(String chatId, String[][] serialsId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.SEND_ID_SERIAL_FOR_UNSUBSCRIBE));
        sendMessage.setReplyMarkup(keyboardProvider.variablesManyRowsKeyboard(serialsId));
        return sendMessage;
    }

    public SendMessage getListOfAllSerialsWithIdMessage(String chatId, String[] serials) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.LIST_OF_ALL_SERIALS) + String.join("\n", serials));
        sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        return sendMessage;
    }

    public SendMessage getUnsubscribeAllMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.ANSWER_UNSUBSCRIBE_ALL));
        sendMessage.setReplyMarkup(keyboardProvider.keyboardUnsubscribeAll());
        return sendMessage;
    }

    public SendPhoto getSerialPosterMessage(String chatId) {
        InputFile photoFile = new InputFile(new File("src/main/resources/test/photo.png"));
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(photoFile);
        sendPhoto.setChatId(chatId);
        sendPhoto.setReplyMarkup(keyboardProvider.keyboardApproveSerial());
        return sendPhoto;
    }

    public SendMessage getItIsNeededSerialMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.NEEDED_SERIAL));
        sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        return sendMessage;
    }

    public SendMessage getErrorMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Ошибочка вышла\nВозможно функционал еще не готов \uD83D\uDE2D");
        return sendMessage;
    }

    public SendMessage getHasBeenSubscribeMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.HAS_BEEN_SUBSCRIBE));
        sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        return sendMessage;
    }

    public SendMessage getChooseVariantFromOptionsMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.CHOOSE_A_VARIANT_FROM_OPTIONS));
        sendMessage.setReplyMarkup(keyboardProvider.keyboardApproveSerial());
        return sendMessage;
    }

    public SendMessage getSerialNotFoundMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.SERIAL_NOT_FOUND));
        sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        return sendMessage;
    }

    public SendMessage getMenuMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.SHOW_MENU));
        sendMessage.setReplyMarkup(keyboardProvider.keyboardFullMenu());
        return sendMessage;
    }

    public SendMessage getHelloMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.HELLO));
        sendMessage.setReplyMarkup(keyboardProvider.emptyKeyboard());
        return sendMessage;
    }

    public SendMessage getHelloInputMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messages.get(ActionMessages.HELLO_INPUT));
        sendMessage.setReplyMarkup(keyboardProvider.keyboardMenu());
        return sendMessage;
    }

}