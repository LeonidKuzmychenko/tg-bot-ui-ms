package com.example.tgserialsbot.bot.services;

import com.example.tgserialsbot.bot.model.BotUser;
import com.google.common.cache.Cache;
import org.springframework.stereotype.Service;

@Service
public class BotUserService {

    public final Cache<String, BotUser> cache;

    public BotUserService(Cache<String, BotUser> cache) {
        this.cache = cache;
    }

    public BotUser getUser(String chatId){
        BotUser user = cache.getIfPresent(chatId);
        if (user == null) {
            user = new BotUser();
            cache.put(chatId, user);
        }
        return user;
    }
}