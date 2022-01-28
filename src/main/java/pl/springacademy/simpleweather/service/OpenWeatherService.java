package pl.springacademy.simpleweather.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import pl.springacademy.simpleweather.configuration.OpenWeatherConfiguration;
import pl.springacademy.simpleweather.controller.model.WeatherData;
import pl.springacademy.simpleweather.service.model.OpenWeatherResponse;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class OpenWeatherService {

    private final static String BY_CITY_ENDPOINT = "?q={city}&appid={apiKey}&units=metric";
    private final static String ICON_ENDPOINT_SUFFIX = "@2x.png";

    private final RestTemplate restTemplate;
    private final OpenWeatherConfiguration configuration;

    public WeatherData getWeatherByCity(final String city) {

        final String url = configuration.getBaseUrl() + BY_CITY_ENDPOINT;

        final ResponseEntity<OpenWeatherResponse> response;

        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    OpenWeatherResponse.class,
                    city,
                    configuration.getApiKey()
            );
        } catch (final HttpClientErrorException.NotFound e) {
            return WeatherData.builder()
                    .errorMessage("City " + city + " was not found")
                    .build();
        }

        return bindResponseToWeatherData(response.getBody());
    }

    private WeatherData bindResponseToWeatherData(final OpenWeatherResponse response) {

        if (response.getCod() != HttpStatus.OK.value()) {
            return WeatherData.builder()
                    .errorMessage(response.getMessage())
                    .build();
        }

        return WeatherData.builder()
                .temp(response.getMain().getTemp())
                .perceivedTemp(response.getMain().getFeelsLike())
                .tempMin(response.getMain().getTempMin())
                .tempMax(response.getMain().getTempMax())
                .humidity(response.getMain().getHumidity())
                .pressure(response.getMain().getPressure())
                .windDirection(response.getWind().getDeg())
                .windSpeed(response.getWind().getSpeed())
                .iconUrl(prepareIconUrl(response.getWeather().get(0).getIcon()))
                //                .sunrise(response.getSys().getSunrise())
                //                .sunset(response.getSys().getSunset())
                .city(response.getName())
                .country(response.getSys().getCountry())
                .errorMessage(response.getMessage())
                .build();
    }

    private String prepareIconUrl(final String icon) {

        return configuration.getIconUrl() + icon + ICON_ENDPOINT_SUFFIX;
    }
}
