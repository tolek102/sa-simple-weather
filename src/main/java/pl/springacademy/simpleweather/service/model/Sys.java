package pl.springacademy.simpleweather.service.model;

import lombok.Data;

@Data
public class Sys {

	private Integer sunrise;
	private Integer sunset;
	private String country;
}