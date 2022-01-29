package pl.springacademy.simpleweather.controller.model;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Value;
import pl.springacademy.simpleweather.service.model.OpenWeatherResponse;

@Value
@Builder
public class WeatherData {

    private final static String ICON_ENDPOINT_SUFFIX = "@2x.png";

    Double temp;
    Double perceivedTemp;
    Double tempMin;
    Double tempMax;
    Integer humidity;
    Integer pressure;

    Integer windDirection;
    Double windSpeed;

    String iconUrl;

    LocalTime sunrise;
    LocalTime sunset;

    String city;
    String country;

    String errorMessage;

    public static WeatherData forError(final String errorMessage) {
        return WeatherData.builder()
                .errorMessage(errorMessage)
                .build();
    }

    public static WeatherData from(final OpenWeatherResponse openWeatherResponse, final String iconBaseUrl) {

        return WeatherData.builder()
                .temp(openWeatherResponse.getMain().getTemp())
                .perceivedTemp(openWeatherResponse.getMain().getFeelsLike())
                .tempMin(openWeatherResponse.getMain().getTempMin())
                .tempMax(openWeatherResponse.getMain().getTempMax())
                .humidity(openWeatherResponse.getMain().getHumidity())
                .pressure(openWeatherResponse.getMain().getPressure())
                .windDirection(openWeatherResponse.getWind().getDeg())
                .windSpeed(openWeatherResponse.getWind().getSpeed())
                .iconUrl(prepareIconUrl(openWeatherResponse.getWeather().get(0).getIcon(), iconBaseUrl))
                //                .sunrise(openWeatherResponse.getSys().getSunrise())
                //                .sunset(openWeatherResponse.getSys().getSunset())
                .city(openWeatherResponse.getName())
                .country(openWeatherResponse.getSys().getCountry())
                .errorMessage(openWeatherResponse.getMessage())
                .build();
    }

    private static String prepareIconUrl(final String icon, final String iconBaseUrl) {

        return iconBaseUrl + icon + ICON_ENDPOINT_SUFFIX;
    }
}
