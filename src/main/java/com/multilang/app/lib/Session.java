package com.multilang.app.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import javax.servlet.http.*;

public class Session
{
	private HashMap<String, SessionEntity> entities = new HashMap();

	private String request_session_attribute_name = "user_language";

//	Entities -----------------------------------------------------
	public HashMap<String, SessionEntity> getEntities()
	{
		return this.entities;
	}

	public SessionEntity getEntity(String uuid)
	{
		if (this.entities.containsKey(uuid)) {
			return this.entities.get(uuid);
		} else {
			return null;
		}
	}

	public SessionEntity getAppropriateEntity(HttpServletRequest request)
	{
		String sessionUuid = null;

		if (request.getAttribute(this.getRequest_session_attribute_name()) != null) {
			sessionUuid = (String) request.getAttribute(this.getRequest_session_attribute_name());
		} else {
			Cookie cookie = this.getCookie(request);
			if (cookie == null) {
				return null;
			}
			sessionUuid = cookie.getValue();
		}

		SessionEntity entity = this.getEntities().get(sessionUuid);

		return (entity != null ? entity : null);
	}

	public SessionEntity addEntity(HttpServletResponse response)
	{
		AppContext context = AppContext.getInstance();

		String cookieUuid = this.generateSessionUuid();
		this.entities.put(cookieUuid, new SessionEntity(cookieUuid));

		Cookie cookie = new Cookie(context.getAppConfig().getSession_cookie_name(), cookieUuid);
		cookie.setPath("/");
		response.addCookie(cookie);

		return this.entities.get(cookieUuid);
	}

	public boolean removeEntity(String uuid)
	{
		if (!this.getEntities().containsKey(uuid)) {
			return false;
		}

		this.getEntities().remove(uuid);

		return true;
	}

	public void setEntityLanguage(String uuid, String language) throws Exception
	{
		this.getEntity(uuid).setLanguage(language);
	}

	public void setEntityRedirectUrl(String uuid, String url) throws Exception
	{
		this.getEntity(uuid).setRedirectUrl(url);
	}

//	Additional methods --------------------------------------------
	public String generateSessionUuid()
	{
		return UUID.randomUUID().toString();
	}

	public Cookie getCookie(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			AppContext context = AppContext.getInstance();

			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(context.getAppConfig().getSession_cookie_name())
					&& this.getEntities().containsKey(cookie.getValue())
				) {
					return cookie;
				}
			}
		}

		return null;
	}

	public String getRequest_session_attribute_name()
	{
		return this.request_session_attribute_name;
	}
}