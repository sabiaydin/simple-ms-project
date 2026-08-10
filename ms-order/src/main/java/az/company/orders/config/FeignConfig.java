package az.company.orders.config;

import az.company.orders.client.decoder.CustomErrorDecoder;
import feign.Client;
import feign.codec.ErrorDecoder;
import feign.hc5.ApacheHttp5Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
/*
    @Bean
    public Client feignClient() {
        return new ApacheHttp5Client();
    }*/

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

}
