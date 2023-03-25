package com.codingjump.config;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;

@Configuration
public class FileWatcher {
    private FileReadingMessageSource fileReadingMessageSource() {
        FileReadingMessageSource ms = new FileReadingMessageSource();
        ms.setDirectory(new File("/opt/inputDir"));
        ms.setFilter(new SimplePatternFileListFilter("*.txt"));
        return ms;
    }

    @Bean("inputFlow")
    public IntegrationFlow inputFlow(MyFileHandler myFileHandler) {
        return IntegrationFlow
                .from(fileReadingMessageSource(), c -> c.poller(Pollers.fixedDelay(10000)))
                .channel(new DirectChannel())
                .handle(myFileHandler).get();
    }
}
