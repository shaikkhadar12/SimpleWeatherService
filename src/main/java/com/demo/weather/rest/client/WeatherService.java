package com.demo.weather.rest.client;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.demo.weather.model.Weather;
import com.demo.weather.model.WeatherForecast;

@Service
public class WeatherService {
	@Value("${app.weather.api.key}")
	private String apiKey;

	@Autowired
	private final RestTemplate restTemplate;

	private static final String WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?zip={zip},{country}&APPID={key}";

	private static final String FORECAST_API = "http://api.openweathermap.org/data/2.5/forecast?zip={zip},{country}&APPID={key}";

	private static final Logger LOG = LoggerFactory.getLogger(WeatherService.class);

	public WeatherService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public Weather getCurrentWeather(String zip, String country) throws HttpClientErrorException {
		LOG.info("Requesting current weather for {}/{}", country, zip);
		URI url = new UriTemplate(WEATHER_API).expand(zip, country, this.apiKey);
		Weather weather = invokeAPI(url, Weather.class);
		return weather;
	}

	public WeatherForecast getWeatherForecast(String zip, String country) throws HttpClientErrorException {
		LOG.info("Requesting weather forecast for {}/{}", country, zip);
		URI url = new UriTemplate(FORECAST_API).expand(zip, country, this.apiKey);
		WeatherForecast weatherForecast = invokeAPI(url, WeatherForecast.class);
		return weatherForecast;
	}

	private <T> T invokeAPI(URI url, Class<T> responseType) {
		T weather = null;
		try {
			RequestEntity<?> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
			ResponseEntity<T> exchange = this.restTemplate.exchange(request, responseType);
			weather = exchange.getBody();
		} catch (HttpClientErrorException e) {
			LOG.error("An error occurred while calling openweathermap.org API endpoint:  " + e.getMessage());
			throw e;// TODO customize the exception
		}

		return weather;
	}

}
