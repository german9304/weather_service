package com.service.weather.forecast;

import com.service.weather.forecast.entities.ForecastEntity;
import com.service.weather.forecast.entities.GridendpointsEntity;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.ArrayList;


public interface ForecastService {

    Mono<ResponseEntity<ForecastEntity>> getForecast(String url);

    Mono<GridendpointsEntity> getGridEndpoints(Double latitude, Double longitude);
}
