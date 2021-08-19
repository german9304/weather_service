package com.service.weather.forecast;

import lombok.Data;

import java.util.ArrayList;

@Data
class Periods {
    private String name;
    private String context;
    private String startTime;
    private String endTime;
    private String detailedForecast;
}

@Data
class Properties {
    private ArrayList<Periods> periods;
}

@Data
public class Forecast {
    private Properties properties;
}
