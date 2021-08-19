package com.service.weather.geocoding;

import com.google.maps.GeoApiContext;
import com.google.maps.model.GeocodingResult;

import java.util.Optional;

public interface GecodingService {

    GeoApiContext getGeoApiContext();

    Optional<GeocodingResult[]> fetchGeoCoding(String address);
}
