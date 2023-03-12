package com.codingjump;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Sender {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Scheduled(fixedRate = 10000)
	public void sendEvent() throws Exception {
		String msg = "Hello World " + System.currentTimeMillis();
		jmsTemplate.send("test", s -> s.createTextMessage(msg));
		log.info("Sending Message: {}", msg);
	}
}
