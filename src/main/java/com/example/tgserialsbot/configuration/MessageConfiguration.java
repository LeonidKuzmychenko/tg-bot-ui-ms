package com.example.tgserialsbot.configuration;

import com.example.tgserialsbot.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class MessageConfiguration {

    @Bean("tgMessages")
    public Map<String, String> provideMessages(ObjectMapper mapper) throws IOException {
        return parse(mapper, "src/main/resources/tg_messages_ru.json");
    }

    @Bean("tgAnswerVariables")
    public Map<String, String> provideAnswerVariables(ObjectMapper mapper) throws IOException {
        return parse(mapper, "src/main/resources/tg_answer_variables_ru.json");
    }

    private Map<String, String> parse(ObjectMapper objectMapper, String path) throws IOException {
        MessageDto[] messages = objectMapper.readValue(new File(path), MessageDto[].class);
        return Arrays.stream(messages).collect(Collectors.toMap(MessageDto::getKey, MessageDto::getValue));
    }

}