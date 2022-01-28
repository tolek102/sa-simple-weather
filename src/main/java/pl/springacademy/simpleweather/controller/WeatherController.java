package pl.springacademy.simpleweather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import pl.springacademy.simpleweather.controller.model.WeatherData;
import pl.springacademy.simpleweather.service.WeatherService;

import static java.util.Objects.nonNull;

@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/")
    public String getMainPage(@RequestParam(required = false) final String message, final Model model) {
        model.addAttribute("message", message);
        model.addAttribute("weatherData", null);

        return "main";
    }

    @GetMapping("/weather")
    public String getWeatherByCity(@RequestParam final String city, final Model model, final RedirectAttributes attributes) {
//        final WeatherData weatherData = weatherService.getWeatherByCity(city);
        final WeatherData weatherData = WeatherData.builder().country("PL").city(city).build();

        if (nonNull(weatherData.getErrorMessage())) {
            attributes.addAttribute("message", weatherData.getErrorMessage());
            return "redirect:/weather";
        }

        model.addAttribute("weatherData", weatherData);

        return "main";
    }
}
