package com.user.service.UserService.config;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigClassBean {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalancerClient) {
//        return new LoadBalancerInterceptor((org.springframework.cloud.client.loadbalancer.LoadBalancerClient) loadBalancerClient);
//    }

//    @Bean
//    public RestTemplate loadBalancedRestTemplate(LoadBalancerInterceptor loadBalancerInterceptor) {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(Collections.singletonList(loadBalancerInterceptor));
//        return restTemplate;
//    }

//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(LoadBalancerInterceptor loadBalancerInterceptor) {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(Collections.singletonList(loadBalancerInterceptor));
//        return restTemplate;
//    }


    @Bean
    @Scope("prototype")
    public Logger logger() {
        return LoggerFactory.getLogger("UserServiceImpl");
    }
}
