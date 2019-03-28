package com.demo.weather;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		registerEndpoints();
	}

	private void registerEndpoints() {
		BeanConfig swaggerConfig = new BeanConfig();
		swaggerConfig.setBasePath("/api");
		SwaggerConfigLocator.getInstance().putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, swaggerConfig);

		packages(getClass().getPackage().getName(), ApiListingResource.class.getPackage().getName());
	}
}
