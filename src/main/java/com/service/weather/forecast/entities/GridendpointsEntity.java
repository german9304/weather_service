package com.service.weather.forecast.entities;

import lombok.Data;

@Data
class GridendpointsRelativeLocationPropertiesEntity {
    private String city;
    private String state;
}

@Data
class GridendpointsRelativeLocationEntity {
    private GridendpointsRelativeLocationPropertiesEntity properties;
}

@Data
class GridendpointsPropertiesEntity {
    private String cwa;
    private String gridId;
    private String gridX;
    private String gridY;
    private String forecast;
    private String forecastHourly;
    private GridendpointsRelativeLocationEntity relativeLocation;
}

@Data
public class GridendpointsEntity {
    private GridendpointsPropertiesEntity properties;

    public String getForecast() {
        return this.properties.getForecast();
    }
}
