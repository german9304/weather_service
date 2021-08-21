package com.service.weather.forecast;

import com.service.weather.forecast.entities.ForecastEntity;
import com.service.weather.forecast.entities.GridendpointsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class ForecastServiceImpl implements ForecastService {

    private final WebClient webClient;

    ForecastServiceImpl(
            WebClient.Builder webClient
    ) {
        this.webClient = webClient.baseUrl("https://api.weather.gov").build();
    }

    public WebClient getClient() {
        return this.webClient;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public Mono<ResponseEntity<GridendpointsEntity>> getGridEndpoints(Double latitude, Double longitude) {
        this.log.info("requesting forecast");
        return this.webClient.get().uri((uriBuilder) -> {
            var urib = uriBuilder.path("/points/{x},{y}")
                    .build(
                            String.format("%.4f", latitude),
                            String.format("%.4f", longitude)
                    );
            this.log.info("current path " + urib.getPath());
            return urib;
        })
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(GridendpointsEntity.class);
    }

    public Mono<ResponseEntity<ForecastEntity>> getForecast(String url) {
        this.log.info("requesting forecast");
        return this.webClient.get().uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(ForecastEntity.class);
    }

}
