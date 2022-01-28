package pl.springacademy.simpleweather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import pl.springacademy.simpleweather.controller.model.WeatherData;
import pl.springacademy.simpleweather.service.OpenWeatherService;

@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final OpenWeatherService weatherService;

    @GetMapping("/")
    public String getMainPage(final Model model) {
        model.addAttribute("weatherData", null);

        return "main";
    }

    @GetMapping("/weather")
    public String getWeatherByCity(@RequestParam final String city, final Model model) {

        final WeatherData weatherData = weatherService.getWeatherByCity(city);

        model.addAttribute("weatherData", weatherData);

        return "main";
    }
}
