package com.demo.weather.rest.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.demo.weather.model.ResponseMessage;
import com.demo.weather.model.Weather;
import com.demo.weather.model.WeatherForecast;
import com.demo.weather.rest.client.WeatherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/weather")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Weather API", produces = "application/json")
public class WeatherServiceController {

	@Autowired
	WeatherService service;

	@GET
	@Path("/ping")
	@ApiOperation( // Swagger Annotation
			value = "Get OK message if application is live", response = Response.class)
	@ApiResponses(value = { // Swagger Annotation
			@ApiResponse(code = 200, message = "Resource found"), })
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping() {
		ResponseMessage response = new ResponseMessage();
		response.setMsg("OK");
		return Response.status(Response.Status.OK).entity(response).build();
	}

	@GET
	@Path("/current/{zip}/{country}")
	@ApiOperation(value = "Get current weather ex: I/P like 08536/US", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource NOT found"), })
	@Produces(MediaType.APPLICATION_JSON)
	public Response current(@ApiParam @PathParam("zip") String zip,
			@ApiParam("2 letter country code") @PathParam("country") String country) {
		// Validate input here in controller

		Weather weather = null;
		try {
			weather = service.getCurrentWeather(zip, country);
		} catch (HttpClientErrorException e) {
			ResponseMessage response = new ResponseMessage();
			response.setMsg(e.getStatusCode().toString());
			return Response.status(Response.Status.NOT_FOUND).entity(response).build();
		}
		return Response.status(Response.Status.OK).entity(weather).build();
	}

	@GET
	@Path("/forecast/{zip}/{country}")
	@ApiOperation(value = "Get weather forecast ex: I/P like 08536/US", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource NOT found"), })
	@Produces(MediaType.APPLICATION_JSON)
	public Response forecast(@ApiParam @PathParam("zip") String zip,
			@ApiParam("2 letter country code") @PathParam("country") String country) {
		// Validate input here in controller
		WeatherForecast weatherForecast = null;
		try {
			weatherForecast = service.getWeatherForecast(zip, country);
		} catch (HttpClientErrorException e) {
			ResponseMessage response = new ResponseMessage();
			response.setMsg(e.getStatusCode().toString());
			return Response.status(Response.Status.NOT_FOUND).entity(response).build();
		}
		return Response.status(Response.Status.OK).entity(weatherForecast).build();
	}

	@GET
	@Path("/forecastForDays/{days}/{zip}/{country}")
	@ApiOperation(value = "Get weather forecast for next no. of days - NOT IMPLEMENTED", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource NOT found"), })
	@Produces(MediaType.APPLICATION_JSON)
	public Response forecast(@ApiParam @PathParam("days") String days, @ApiParam @PathParam("zip") String zip,
			@ApiParam("2 letter country code") @PathParam("country") String country) {
		ResponseMessage msg = new ResponseMessage();
		msg.setMsg("API IMPLEMENTION IN PROGRESS");
		return Response.status(Response.Status.OK).entity(msg).build();	}

	@GET
	@Path("/warmestDay/{days}/{zip}/{country}")
	@ApiOperation(value = "misc method to know the warmest day in next n days - NOT IMPLEMENTED", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource NOT found"), })
	@Produces(MediaType.APPLICATION_JSON)
	public Response warmestDay(@ApiParam @PathParam("days") String days, @ApiParam @PathParam("zip") String zip,
			@ApiParam("2 letter country code") @PathParam("country") String country) {
		ResponseMessage msg = new ResponseMessage();
		msg.setMsg("API IMPLEMENTION IN PROGRESS");
		return Response.status(Response.Status.OK).entity(msg).build();	}

	@GET
	@Path("/coldestDay/{days}/{zip}/{country}")
	@ApiOperation(value = "misc method to know the coldest day in next n days - NOT IMPLEMENTED", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource NOT found"), })
	@Produces(MediaType.APPLICATION_JSON)
	public Response coldestDay(@ApiParam @PathParam("days") String days, @ApiParam @PathParam("zip") String zip,
			@ApiParam("2 letter country code") @PathParam("country") String country) {
		ResponseMessage msg = new ResponseMessage();
		msg.setMsg("API IMPLEMENTION IN PROGRESS");
		return Response.status(Response.Status.OK).entity(msg).build();
	}

}
