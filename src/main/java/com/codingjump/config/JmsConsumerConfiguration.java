package com.codingjump.config;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

@EnableJms
@Configuration
public class JmsConsumerConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(JmsConsumerConfiguration.class);

    @Value("${solace.jms.host}")
    String Host;

    @Bean
    ConnectionFactory getConnectionFactory() {
        try {
            SolConnectionFactory cf = SolJmsUtility.createConnectionFactory();
            cf.setHost(Host);
            cf.setUsername("admin");
            cf.setPassword("admin");
            cf.setDirectTransport(false);
            cf.setVPN("default");
            return cf;
        } catch (Exception e) {
            return null;
        }
    }

    // Example configuration of the ConnectionFactory: we instantiate it here
    // ourselves and set an error handler
    @Bean
    public DefaultJmsListenerContainerFactory cFactory(ConnectionFactory connectionFactory,
            DemoErrorHandler errorHandler) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(errorHandler);
        return factory;
    }

    @Service
    public class DemoErrorHandler implements ErrorHandler {

        public void handleError(Throwable t) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(os);
            t.printStackTrace(ps);
            try {
                String output = os.toString("UTF8");
                logger.error("============= Error processing message: " + t.getMessage() + "\n" + output);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }
}
