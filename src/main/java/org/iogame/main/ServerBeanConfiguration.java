package org.iogame.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerBeanConfiguration {

    @Bean
    public Server getServer() {
        return Server.getInstance();
    }
}
