package com.multilang.app.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.multilang.app.interceptor.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer 
{
	@Override
	public void addInterceptors(InterceptorRegistry registry) 
	{
//	Main
		ArrayList<String> excludedPaths = new ArrayList<String>();
		excludedPaths.add("/css/*");
		excludedPaths.add("/css/bootstrap/*");
		excludedPaths.add("/js/*");
		excludedPaths.add("/images/*");
		excludedPaths.add("/favicon.ico");
//		excludedPaths.add("/error");
		
	    registry.addInterceptor(new MainInterceptor()).excludePathPatterns(excludedPaths);

//	Change locale
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
}