package pl.springacademy.simpleweather.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import pl.springacademy.simpleweather.configuration.OpenWeatherConfiguration;
import pl.springacademy.simpleweather.service.model.OpenWeatherResponse;

@Service
@RequiredArgsConstructor
public class OpenWeatherService {

    private final static String BY_CITY_ENDPOINT = "?q={city}&appid={apiKey}&units=metric";

//    private final RestTemplate restTemplate;
    private final OpenWeatherConfiguration configuration;

    public OpenWeatherResponse getOpenWeatherDataByCity(final String city) {

        final RestTemplate restTemplate = new RestTemplate();

        final String url = configuration.getBaseUrl() + BY_CITY_ENDPOINT;

        final ResponseEntity<OpenWeatherResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                OpenWeatherResponse.class,
                city,
                configuration.getApiKey()
        );

        return response.getBody();
    }
}
