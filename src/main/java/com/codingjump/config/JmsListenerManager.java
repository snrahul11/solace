package com.codingjump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.stereotype.Component;

@Component
public class JmsListenerManager {
    @Autowired
    JmsListenerEndpointRegistry jmsListenerEndpointRegistry;

    public void stopListener() {
        jmsListenerEndpointRegistry.stop();
    }

    public void startListener() {
        jmsListenerEndpointRegistry.start();
    }
}
