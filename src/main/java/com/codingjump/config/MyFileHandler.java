package com.codingjump.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class MyFileHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        // Your Logic Here
    }
}
