package com.multilang.app.config;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties("app")
public class AppConfig 
{
    private String session_cookie_name;

	public String getSession_cookie_name()
	{
		return this.session_cookie_name;
	}
	
	public void setSession_cookie_name(String session_cookie_name)
	{
		this.session_cookie_name = session_cookie_name;
	}
}