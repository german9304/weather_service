package com.service.weather.forecast;

import com.service.weather.forecast.entities.ForecastEntity;
import com.service.weather.forecast.entities.ForecastEntity;
import com.service.weather.geocoding.GecodingService;
import lombok.extern.slf4j.Slf4j;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

@Slf4j
@WebMvcTest(ForecastController.class)
public class ForecastControllerTests {

    private final String api = "/api/forecast";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForecastService forecastService;

    @MockBean
    private GecodingService gecodingService;

    @BeforeEach
    public void beforeEach() {
        this.log.info("Before each tests");
    }

    @Test
    public void forecastShouldReturn200() throws Exception {

        String url = "/api/forecast/gridpoints?latitude=3&longitude=4";
        ForecastEntity forecastMock = new ForecastEntity();
        forecastMock.setProperties(forecastMock.getProperties());
        Mono<ResponseEntity<ForecastEntity>> responseEntity = Mono.just(
                ResponseEntity.status(HttpStatus.OK).body(forecastMock)
        );

        when(forecastService.getForecast(url)).thenReturn(responseEntity);

        this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());
    }
}
