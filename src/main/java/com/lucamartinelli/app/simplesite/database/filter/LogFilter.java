package com.lucamartinelli.app.simplesite.database.filter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(9)
public class LogFilter implements ContainerRequestFilter, ContainerResponseFilter {
	
	private static final Logger log = Logger.getLogger(LogFilter.class.getCanonicalName());
	private static final Level level = Level.INFO;

	@Override
	  public void filter(ContainerRequestContext reqContext) {
	      log.log(level, "\n---- request info start ----");
	      log(reqContext.getUriInfo(), reqContext.getHeaders());
	      log.log(level, "\n----  request info end  ----");
	  }

	  @Override
	  public void filter(ContainerRequestContext reqContext,
	                     ContainerResponseContext resContext) {
		  log.log(level, "\n---- response info start ----");
	      log(reqContext.getUriInfo(), resContext.getHeaders(), resContext.getEntity());
	      log.log(level, "\n----  response info end  ----");
	  }
	  
	  private void log(UriInfo uriInfo, MultivaluedMap<String, ?> headers, Object entity) {
		  final StringBuffer b = new StringBuffer('\n');
		  b.append("Path: " + uriInfo.getPath());
		  b.append("\nHeaders:");
	      headers.entrySet().forEach(h ->  b.append("\n - " + h.getKey() + ": " + h.getValue()));
	      if (entity != null)
	    	  b.append("\nBody: " + entity);
	      log.log(level, b.toString());
	  }

	  private void log(UriInfo uriInfo, MultivaluedMap<String, ?> headers) {
	      log(uriInfo, headers, null);
	  }
}
