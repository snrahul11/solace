package com.codingjump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    JmsListenerManager jmsListenerManager;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        jmsListenerManager.startListener();    
    }
}
