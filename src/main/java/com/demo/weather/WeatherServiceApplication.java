package com.demo.weather;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class WeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApplication.class, args);

	}

	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.demo.weather.rest.controller"))
				.paths(PathSelectors.any()).build().enable(true).apiInfo(apiInfo());
	}

	public ApiInfo apiInfo() {
		StringVendorExtension vendorExtension = new StringVendorExtension("", "");
		Collection<VendorExtension> vendorExtensions = new ArrayList<>();
		vendorExtensions.add(vendorExtension);

		return new ApiInfo("Weather", "Sample Weather Service API", "1.0", "Demo", null, "Apache 2.0", "www.apache.org",
				vendorExtensions);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
