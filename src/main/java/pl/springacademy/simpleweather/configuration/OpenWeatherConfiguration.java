package pl.springacademy.simpleweather.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "openweather")
public class OpenWeatherConfiguration {

    String apiKey;
    String baseUrl;
    String iconUrl;
}
