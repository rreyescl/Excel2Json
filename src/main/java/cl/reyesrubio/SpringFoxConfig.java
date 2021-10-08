package cl.reyesrubio;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Autowired
	private MessageSource messages;
	
	@Value("${http.status.code.200}")
	private String STATUS_CODE_200;
	@Value("${http.status.code.401}")
	private String STATUS_CODE_401;
	@Value("${http.status.code.403}")
	private String STATUS_CODE_403;
	@Value("${http.status.code.404}")
	private String STATUS_CODE_404;
	@Value("${http.status.code.500}")
	private String STATUS_CODE_500;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("cl.reyesrubio"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(apiInfo()).useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						newArrayList(
								new ResponseMessageBuilder().code(200).message(messages.getMessage(STATUS_CODE_200, null, Locale.getDefault())).build()
								, new ResponseMessageBuilder().code(401).message(messages.getMessage(STATUS_CODE_401, null, Locale.getDefault())).build()
								, new ResponseMessageBuilder().code(403).message(messages.getMessage(STATUS_CODE_403, null, Locale.getDefault())).build()
								, new ResponseMessageBuilder().code(404).message(messages.getMessage(STATUS_CODE_404, null, Locale.getDefault())).build()
								, new ResponseMessageBuilder().code(500).message(messages.getMessage(STATUS_CODE_500, null, Locale.getDefault())).build()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(" REST API", "API para realizar cualquier cosa ajja.", "API V1",
				"Terms of service",
				new Contact("Ricardo Reyes", "", "ricardo.reyesr@protonmail.com"), "License of API",
				"API license URL", Collections.emptyList());
	}
}