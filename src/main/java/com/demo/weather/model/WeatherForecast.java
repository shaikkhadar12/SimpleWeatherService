package com.demo.weather.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherForecast implements Serializable {

	private String name;

	private List<Weather> weatherEntries = new ArrayList<>();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("entries")
	public List<Weather> getEntries() {
		return this.weatherEntries;
	}

	@JsonSetter("list")
	public void setEntries(List<Weather> weatherEntries) {
		this.weatherEntries = weatherEntries;
	}

	@JsonProperty("city")
	public void setCity(Map<String, Object> city) {
		setName(city.get("name").toString());
	}

}