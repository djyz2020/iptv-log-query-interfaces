package com.nudt.query.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalCorsConfig {

    /**
     * 跨域(CORS)全局设置
     * @return
     */
    /* @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("*//**", config);
        return new CorsFilter(configSource);
    }*/
}