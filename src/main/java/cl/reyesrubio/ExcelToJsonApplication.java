package cl.reyesrubio;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class ExcelToJsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelToJsonApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(ExcelToJsonApplication.class);
	}

	@Bean
	public LocaleResolver localeResolver() {
		
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("es", "ES"));
		
		return localeResolver;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		resource.setUseCodeAsDefaultMessage(true);
		
		return resource;
	}
}
