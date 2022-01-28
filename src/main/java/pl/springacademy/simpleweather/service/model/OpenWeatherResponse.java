package pl.springacademy.simpleweather.service.model;

import java.util.List;

import lombok.Data;

@Data
public class OpenWeatherResponse {

	private Main main;
	private Sys sys;
//	private Coord coord;
	private List<WeatherItem> weather;
	private String name;
	private int cod;
	private String message;
	private String base;
	private Wind wind;
}