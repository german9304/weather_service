package com.service.weather.geocoding;

import com.google.maps.GeoApiContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class GeocodingConfig {

    @Value("${googleApiKey}")
    private String googleApiKey;

    @Bean
    public GeoApiContext getGeoApiContext() {
        this.log.info("GOOGLE PI " + this.googleApiKey);
        return new GeoApiContext.Builder()
                .apiKey(this.googleApiKey)
                .build();
    }

}
