package com.example.tgserialsbot.bot.services;

import com.example.tgserialsbot.bot.model.BotUser;
import com.example.tgserialsbot.http.TestService;
import com.example.tgserialsbot.http.model.UserUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class BotUserService {

    private final TestService testService;

    public BotUserService(TestService testService) {
        this.testService = testService;
    }

    public BotUser getUser(String chatId) {
        try {
            Response<BotUser> botUserResponse = testService.getUser(chatId).get();
            if (botUserResponse.isSuccessful()) {
                log.info("Получение юзера произошло успешно");
                return botUserResponse.body();
            } else {
                log.error("Получение юзера произошло с ошибкой");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("User do not exist");
    }

    public void setCommand(String chatId, String command) {
        BotUser user = getUser(chatId);
        user.setCommand(command);
        try {
            Response<BotUser> botUserResponse = testService.updateUser(new UserUpdateRequest(chatId, command)).get();
            if (botUserResponse.isSuccessful()) {
                log.info("Юзер успешно обновился");
                return;
            } else {
                log.error("Юзер обновился с ошибкой");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("User update Error");
    }

}