package com.service.weather.geocoding;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * GeoCodingService instantiates google's api GeoApiContext
 * to get geo locations from addresses or viceversa
 */
@Slf4j
@Service
public class GeocodingServiceImpl implements GecodingService {

    private GeoApiContext geoApiContext;

    GeocodingServiceImpl(
            GeoApiContext geoApiContext
    ) {
        this.geoApiContext = geoApiContext;
    }

    /**
     * Gets GeoApiContext api
     * @return GeoApiContext
     */
    @Override
    public GeoApiContext getGeoApiContext() {
            return this.geoApiContext;
    }

    @Override
    public Optional<GeocodingResult[]> fetchGeoCoding(String address) {
        try{
            GeoApiContext geoApiContext = this.getGeoApiContext();
            GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
            return Optional.of(results);
        } catch (Exception e) {
            this.log.error("Error fetching geocontext api " + e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Shuts down geo api context
     */
    public void shutDownGeoApiContextAPI() {
        this.geoApiContext.shutdown();
    }
}
