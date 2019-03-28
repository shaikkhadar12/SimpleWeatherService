package com.demo.weather.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * this model capturing only required fields
 * 
 * @author shaikkhadar
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {

	private String name;

	private Date timestamp;

	private double temperature;

	private String weatherDescription;

	private String countryCode;

	@JsonProperty("name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	@JsonSetter("dt")
	public void setTimestamp(long timestamp) {
		this.timestamp = new Date(timestamp * 1000);
	}

	public double getTemperature() {
		return this.temperature;
	}

	/**
	 * We're conv to fahrenheit. But this can be make dynamic to return in F or
	 * C based on user selection Kelvin to fahrenheit. (K − 273.15) × 9/5 + 32 =
	 * °F
	 * 
	 * @param temperatureKelvin
	 */
	public void setTemperature(double temperatureKelvin) {
		this.temperature = Math.round((temperatureKelvin - 273.15) * (9 / 5) + 32);
	}

	@JsonProperty("main")
	public void setMain(Map<String, Object> main) {
		double kelvinTemp = Double.parseDouble(main.get("temp").toString());
		setTemperature(kelvinTemp);
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	@JsonProperty("sys")
	public void setSys(Map<String, Object> sys) {
		setCountryCode((String) sys.get("country"));

	}

	@JsonProperty("weather")
	public void setWeather(List<Map<String, Object>> weatherEntries) {
		Map<String, Object> weather = weatherEntries.get(0);
		setWeatherDescription((String) weather.get("description"));
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}