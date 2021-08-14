package com.service.weather.forecast;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Data
class forecastData {
    private ArrayList<Periods> data;

    forecastData(ArrayList<Periods> periods) {
        this.data = periods;
    }
}
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
    public Mono<ResponseEntity<Forecast>> getForeCast() {
        return this.forecastService
                .getForcast("gridpoints/SGX/56,13/forecast");
    }

}
