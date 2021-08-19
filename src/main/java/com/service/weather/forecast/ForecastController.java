package com.service.weather.forecast;

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
    private ForecastService forecastService;
    private GecodingService gecodingService;

    ForecastController(
            ForecastService forecastService,
            GecodingService gecodingService
    ) {

        this.forecastService = forecastService;
        this.gecodingService = gecodingService;
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
    public ResponseEntity<String> getWeatherByAddress(
            @RequestParam("address") String address
    ) {
        try {
            var geocodinfResults = this.gecodingService.fetchGeoCoding(address);
            if(geocodinfResults.isPresent()) {
                Double latitude = geocodinfResults.get()[0].geometry.location.lat;
                Double longitude = geocodinfResults.get()[0].geometry.location.lng;
                String gridPoints = String.format("Fetching longitude: %s, latitude: %s", latitude, longitude);
                this.log.info(gridPoints);
                return ResponseEntity.status(HttpStatus.OK).body(gridPoints);
            }
        }catch (Exception e) {
            this.log.error("Error fetching weather by address " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("incorrect address");
    }

    /**
     * Fetch weather report by longitude and latitude
     * @return Mono<ResponseEntity<Forecast>>>
     */
    @GetMapping(api + "/gridpoints")
    public Mono<ResponseEntity<Forecast>> getForeCast(
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude
    ) {
        String gridPointsUrl = String.format("gridpoints/SGX/%s,%s/forecast", latitude, longitude);
        return this.forecastService
                .getForecast(gridPointsUrl);
    }

}
