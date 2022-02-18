package com.example.tgserialsbot.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApplicationProperty {

    @Value("${api.server.host}")
    private String serverHost;
}