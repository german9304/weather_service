package com.service.weather.forecast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastController {

    private final String api ="/api/forecast";

    /**
     * Fetches weather from cities locations
     * @return
     */
    @GetMapping(api)
    public HttpEntity<String> getForeCast() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Forecast weather");
    }

}
