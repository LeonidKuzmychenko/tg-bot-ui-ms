package com.example.tgserialsbot.bot.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessageSender {

    private final AbsSender absSender;

    public MessageSender(AbsSender absSender) {
        this.absSender = absSender;
    }

    public void send(SendMessage message) {
        send(() -> absSender.execute(message));
    }

    public void send(SendPhoto message) {
        send(() -> absSender.execute(message));
    }

    private void send(TelegramMessageSender sender) {
        try {
            sender.send();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private interface TelegramMessageSender {
        void send() throws TelegramApiException;
    }
}