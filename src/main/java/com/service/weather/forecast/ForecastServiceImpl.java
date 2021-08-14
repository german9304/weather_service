package com.service.weather.forecast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Slf4j
@Service
public class ForecastServiceImpl implements ForecastService {

    private WebClient webClient;

    ForecastServiceImpl(
            WebClient.Builder webClient
    ) {
        this.webClient = webClient.baseUrl("https://api.weather.gov").build();
    }

    public WebClient getClient() {
        return this.webClient;
    }

    public Mono<ResponseEntity<Forecast>> getForcast(String url) {
        return this.webClient.get().uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Forecast.class);
    }

}
