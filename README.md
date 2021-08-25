# Weather service

![weather_service](https://github.com/german9304/weather_service/actions/workflows/weather_service_ci.yml/badge.svg)

## Overview

This is a spring application that displays the forecast from longitude and latitude or by address.

When forecast gets fetched from address the api calls Google maps geolocation api to convert addresses into longitude and latitude.

## TODOS:

* Add redis to cache forecast api endpoint by longitude and latitude
* Integrate google maps api to display the weather on client side

## swagger

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

## Resources

* [Weather api](https://www.weather.gov/documentation/services-web-api)

* [Google Maps api](https://developers.google.com/maps)

* [Java Client Geolocation api](https://developers.google.com/maps/documentation/geolocation/overview)


