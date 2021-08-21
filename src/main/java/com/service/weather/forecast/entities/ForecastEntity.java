package com.service.weather.forecast.entities;

import lombok.Data;

import java.util.ArrayList;

@Data
class ForecastPeriodsEntity {
    private String name;
    private String context;
    private String startTime;
    private String endTime;
    private String detailedForecast;
}

@Data
class ForecastPropertiesEntity {
    private ArrayList<ForecastPeriodsEntity> periods;
}

@Data
public class ForecastEntity {
    private ForecastPropertiesEntity properties;
}