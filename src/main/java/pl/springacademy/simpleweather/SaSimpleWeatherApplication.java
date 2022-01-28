package pl.springacademy.simpleweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SaSimpleWeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaSimpleWeatherApplication.class, args);
    }
}
