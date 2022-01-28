package pl.springacademy.simpleweather.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Main{

	private Double temp;
	@JsonProperty("temp_min")
	private Double tempMin;
	private Integer humidity;
	private Integer pressure;
	@JsonProperty("feels_like")
	private Double feelsLike;
	@JsonProperty("temp_max")
	private Double tempMax;
}