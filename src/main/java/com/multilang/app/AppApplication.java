package com.multilang.app;

import com.multilang.app.config.AppConfig;
import com.multilang.app.config.DbConfig;
import com.multilang.app.lib.AppContext;
import com.multilang.app.lib.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.PostConstruct;
import java.util.Locale;

@SpringBootApplication
public class AppApplication
{
	@Autowired
	private DbConfig dbConfig;

	@Autowired
	private AppConfig appConfig;

	public static void main(String[] args)
	{
		SpringApplication.run(AppApplication.class, args);
	}

	/**
	 * After Spring Boot initialization action
	 */
//	@EventListener(ApplicationReadyEvent.class)
	@PostConstruct
	public void init()
	{
		System.out.println("===========================================");
		System.out.println("Init");
		System.out.println("-------------------------------------------");

//	Init context
		AppContext.init(this.dbConfig, this.appConfig);
		AppContext context = AppContext.getInstance();

//	Init data
		System.out.println("Init data...");
		context.setData();
		System.out.println(" - OK");

//	Load languages
		System.out.println("Load languages...");
		context.getData().loadLanguages();
		System.out.println(" - OK");

//	Load menu
		System.out.println("Load menu...");
		context.getData().loadMenu();
		System.out.println(" - OK");

//	Load texts
		System.out.println("Load texts...");
		context.getData().loadTexts();
		System.out.println(" - OK");

//	Load pages
		System.out.println("Load pages...");
		context.getData().loadPages();
		System.out.println(" - OK");

		System.out.println("===========================================");
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}
}
