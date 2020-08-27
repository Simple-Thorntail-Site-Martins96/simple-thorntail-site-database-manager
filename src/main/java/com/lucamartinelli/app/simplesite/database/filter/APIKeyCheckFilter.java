package com.lucamartinelli.app.simplesite.database.filter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Provider
@PreMatching
@Priority(1)
public class APIKeyCheckFilter implements ContainerRequestFilter {
	
	private static final Logger log = Logger.getLogger(APIKeyCheckFilter.class.getCanonicalName());
	
	@Inject
	@ConfigProperty(name="database.service.api.key.value", defaultValue = "123")
	private String apiKey;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		final MultivaluedMap<String, String> headers = requestContext.getHeaders();
		final List<String> apiKeyValues = headers.get("X-API-Key");
		
		if (apiKeyValues == null || apiKeyValues.isEmpty()) {
			log.severe("API Key header is not present");
			throw new NotAuthorizedException("API Key is not present");
		}
		
		if (apiKeyValues.size() > 1) {
			log.severe("API Key header are more than 1, is not accettable");
			throw new NotAuthorizedException("API Key is not correct");
		}
		
		final String headerAPIKey = apiKeyValues.get(0);
		
		if (headerAPIKey == null || !headerAPIKey.equals(apiKey)) {
			log.severe("API Key in input not valid, received: " + headerAPIKey);
			throw new NotAuthorizedException("API Key is not correct");
		}
		
		// All check are done
		return;
	}

}
