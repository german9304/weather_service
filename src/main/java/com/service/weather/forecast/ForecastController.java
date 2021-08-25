package com.service.weather.forecast;

import com.service.weather.forecast.entities.ForecastEntity;
import com.service.weather.forecast.entities.GridendpointsEntity;
import com.service.weather.geocoding.GecodingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
public class ForecastController {

    private final String api ="/api/forecast";
    private final ForecastService forecastService;
    private final GecodingService gecodingService;

    ForecastController(
            ForecastService forecastService,
            GecodingService gecodingService
    ) {

        this.forecastService = forecastService;
        this.gecodingService = gecodingService;
    }

    @GetMapping(api + "/gridendpoints")
    public Mono<GridendpointsEntity> getGridEndpoints(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude
    ) {

        this.log.info("latitude " + latitude);
         return this.forecastService
                .getGridEndpoints(
                        latitude,
                        longitude
                );
    }

    /**
     * Gets weather from address by fetching longitude and latitude
     * from google maps api.
     *
     * Accepts address as queryParameters like:
     *
     * /api/forecast?address=YOURADDRESS
     *
     * TODO: complete this api endpoint
     *
     * @param address is the address to fetch the weather
     * @return Forecast instance
     */
    @GetMapping(api)
    public Mono<Object> getWeatherByAddress(
            @RequestParam("address") String address
    ) {
        try {
            var geocodinfResults = this.gecodingService.fetchGeoCoding(address);
            if(geocodinfResults.isPresent()) {
                Double latitude = geocodinfResults.get()[0].geometry.location.lat;
                Double longitude = geocodinfResults.get()[0].geometry.location.lng;
                String gridPoints = String.format("Fetching latitude: %s, longitude: %s", latitude, longitude);
                this.log.info(gridPoints);
                return this.forecastService
                        .getGridEndpoints(latitude, longitude)
                        .map((response) -> {
                            String forecastUrl = response.getForecast();
                            return this.forecastService.getForecast(forecastUrl);
                        });
            }
        }catch (Exception e) {
            this.log.error("Error fetching weather by address " + e.getMessage());
        }
        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("")
        );
    }

    /**
     * Fetch weather report by longitude and latitude
     * @return Mono<ResponseEntity<Forecast>>>
     */
    @GetMapping(api + "/gridpoints")
    public Mono<ResponseEntity<ForecastEntity>> getForeCast(
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude
    ) {
        String gridPointsUrl = String.format("/gridpoints/SGX/%s,%s/forecast", latitude, longitude);
        return this.forecastService
                .getForecast(gridPointsUrl);
    }

}
