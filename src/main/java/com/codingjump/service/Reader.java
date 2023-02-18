package com.codingjump.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.solacesystems.jcsmp.Message;
import com.solacesystems.jcsmp.TextMessage;

@Component
public class Reader {
    @JmsListener(destination = "SpringTestQueue")
	public void handle(Message message) {

		Date receiveTime = new Date();

		if (message instanceof TextMessage) {
			TextMessage tm = (TextMessage) message;
			try {
				System.out.println(
						"Message Received at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(receiveTime)
								+ " with message content of: " + tm.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(message.toString());
		}
	}

}
