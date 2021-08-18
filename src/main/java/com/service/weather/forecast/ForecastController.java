package com.service.weather.forecast;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class ForecastController {

    private final String api ="/api/forecast";
    private ForecastService forecastService;

    ForecastController(
            ForecastService forecastService
    ) {
        this.forecastService = forecastService;
    }

    /**
     * Fetch weather report
     * @return Mono<ResponseEntity<Forecast>>>
     */
    @GetMapping(api)
    public Mono<ResponseEntity<Forecast>> getForeCast(
            @RequestParam("grid_x") String gridX,
            @RequestParam("grid_y") String gridY
    ) {
        String gridPointsUrl = String.format("gridpoints/SGX/%s,%s/forecast", gridX, gridY);
        this.log.info(gridPointsUrl);
        return this.forecastService
                .getForecast(gridPointsUrl);
    }

}
