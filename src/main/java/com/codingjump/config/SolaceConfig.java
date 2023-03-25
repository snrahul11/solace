package com.codingjump.config;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;
import com.solacesystems.jms.SupportedProperty;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableJms
@Configuration
public class SolaceConfig {
    @Value("${solace.jms.host}")
    String host;

    @Value("${solace.jms.msgVpn}")
    private String vpn;

    @Value("${solace.jms.clientUsername}")
    private String userName;

    @Value("${solace.jms.clientPassword}")
    private String password;

    @SneakyThrows
    @Bean
    ConnectionFactory getConnectionFactory() {
        SolConnectionFactory cf = SolJmsUtility.createConnectionFactory();
        cf.setHost(host);
        cf.setUsername(userName);
        cf.setPassword(password);
        cf.setVPN(vpn);
        cf.setDirectTransport(false);
        return cf;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        CachingConnectionFactory ccf = new CachingConnectionFactory(connectionFactory);
        JmsTemplate jmsTemplate = new JmsTemplate(ccf);
        jmsTemplate.setPubSubDomain(false);
        return jmsTemplate;
    }

    @Bean("jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory getDefaultJmsListenerContainerFactory(ConnectionFactory connectionFactory,
            DemoErrorHandler errorHandler) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAutoStartup(false);
        factory.setErrorHandler(errorHandler);
        factory.setSessionAcknowledgeMode(SupportedProperty.SOL_CLIENT_ACKNOWLEDGE);
        return factory;
    }

    @Service
    public class DemoErrorHandler implements ErrorHandler {
        @SneakyThrows
        public void handleError(Throwable t) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(byteArrayOutputStream);
            t.printStackTrace(ps);
            log.error("Error processing message: {}\nStack trace:\n{}", t.getMessage(),
                    byteArrayOutputStream.toString("UTF8"));
        }
    }
}
