package com.codingjump;

import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Receiver {
    @SneakyThrows
    @JmsListener(destination = "test")
    public void handle(TextMessage message) {
        log.info("Receiving Message: {}", message.getText());
    }
}
