package home.project.tgserialsbot.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.project.tgserialsbot.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class MessageConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean("tgMessages")
    public Map<String, String> provideMessages(ObjectMapper mapper) throws IOException {
        return parse(mapper, "tg_messages_ru.json");
    }

    @Bean("tgAnswerVariables")
    public Map<String, String> provideAnswerVariables(ObjectMapper mapper) throws IOException {
        return parse(mapper, "tg_answer_variables_ru.json");
    }

    private Map<String, String> parse(ObjectMapper objectMapper, String fileName) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        MessageDto[] messages = objectMapper.readValue(inputStream, MessageDto[].class);
        return Arrays.stream(messages).collect(Collectors.toMap(MessageDto::getKey, MessageDto::getValue));
    }
}