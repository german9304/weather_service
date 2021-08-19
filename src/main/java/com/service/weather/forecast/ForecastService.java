package com.service.weather.forecast;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.ArrayList;


public interface ForecastService {

    Mono<ResponseEntity<Forecast>> getForecast(String url);
}
