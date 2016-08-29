package com.ipacc.enterprise.elm.examples;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.ipacc.enterprise.elm.examples.config.ExampleConfig;
@SpringBootApplication
@Component
@RestController
public class OSSpringBootEAPExample extends SpringBootServletInitializer {
	@Autowired
	ExampleConfig exampleConfig;
	//@Autowired
	//Datasource datasource;

	@RequestMapping("/ping")
	public String ping() {
		return "pong";
	}

	
	/**
	 * Main execution method ... only called in embedded tomcat!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OSSpringBootEAPExample.class, args);
	}
	
	/*
     * JBoss EAP 6.2.0 and 6.3.0 mapping.
     */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		try (AnnotationConfigWebApplicationContext context  = new AnnotationConfigWebApplicationContext()) {
			context.register(OSSpringBootEAPExample.class);
			context.setServletContext(servletContext);
			
			Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
			//registration.setLoadOnStartup(1);
			registration.addMapping("/*"); // required JBoss EAP 6.2.0, 6.3.0
			registration.setAsyncSupported(true);

			super.onStartup(servletContext);
		}
	}

	 @Override
	 protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		 return application.sources(OSSpringBootEAPExample.class);
	 }
}
