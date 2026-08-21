package com.rauloliva.footballservice.config;

import com.rauloliva.footballservice.client.AreaHttpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration(proxyBeanMethods = false)
@ImportHttpServices( group = "my-group", types = {
        AreaHttpService.class
})
public class HttpClientConfig {

    @Value("${football.third-party.auth-token}")
    private String authToken;

    @Bean
    public RestClientHttpServiceGroupConfigurer customHeadersConfigurer() {
        return groups -> groups.forEachClient((group, clientBuilder) -> {
            clientBuilder.defaultHeader("X-Auth-Token", authToken);
            clientBuilder.defaultHeader("User-Agent", "Mozilla/5.0");
        });
    }
}
