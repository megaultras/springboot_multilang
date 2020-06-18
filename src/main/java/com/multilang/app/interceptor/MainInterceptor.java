package com.multilang.app.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

import com.multilang.app.lib.Session;
import com.multilang.app.lib.SessionEntity;
import com.multilang.app.lib.Url;
import com.multilang.app.model.LanguagesEntity;
import com.multilang.app.model.PagesEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.ModelAndView;

import com.multilang.app.lib.AppContext;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;

public class MainInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler
	) throws Exception {
		System.out.println("+++++++++++++++++");
		System.out.println("+++ preHandle +++");

		AppContext context = AppContext.getInstance();

//	Setup session
		SessionEntity sessionEntity = context.getSession().getAppropriateEntity(request);
		if (sessionEntity == null) {
			sessionEntity = context.getSession().addEntity(response);
			request.setAttribute(context.getSession().getRequest_session_attribute_name(), sessionEntity.getUuid());

			System.out.println("Setup session: " + sessionEntity.getUuid());
		}

//	Url
		String url = request.getRequestURI().substring(request.getContextPath().length());
		String[] urlVars = url.split("/");
		System.out.println("URL: " + url);

		if (!url.equals("/error") && request.getAttribute("real_url") == null) {
			request.setAttribute("real_url", url);
		}

//	Detect language from URL
		String urlLanguage = null;
		if (urlVars.length > 1) {	//	language is possible in URL
			if (context.getData().getLanguagesCodes().contains(urlVars[1])) {	//	Language found in URL
				urlLanguage = urlVars[1];
				sessionEntity.setLanguage(urlLanguage);
			}
		}

//	Get URL without language
		String clearedUrl = null;
		if (urlLanguage != null) {
			clearedUrl = url.replaceFirst("/" + urlLanguage, "");
			if (clearedUrl.equals("")) {
				clearedUrl = "/";
			}
		} else {
			clearedUrl = url;
		}

//	First entrance on default language
		if (urlLanguage == null && sessionEntity.getLanguage() == null) {
			sessionEntity.setLanguage(context.getData().getDefaultLanguage().getCode());
		}

//	Redirect for default language
		if (urlLanguage != null && urlLanguage.equals(context.getData().getDefaultLanguage().getCode())) {
			System.out.println(" --> redirect: '" + clearedUrl + "'");
			response.sendRedirect(clearedUrl);

			return false;
		}

//	Get current language
		LanguagesEntity currentLanguage = null;
		for (LanguagesEntity lang : context.getData().getLanguages()) {
			if (lang.getCode().equals(sessionEntity.getLanguage())) {
				currentLanguage = lang;
				break;
			}
		}

		if (currentLanguage == null) {
			currentLanguage = context.getData().getDefaultLanguage();
		}

		request.setAttribute("currentLanguage", currentLanguage);
		request.setAttribute("Url", new Url(currentLanguage.getCode()));

//	Redispatch to URL without language segment
		if (urlVars.length > 1 && context.getData().getLanguagesCodes().contains(urlVars[1])) {
			System.out.println(" -> dispatch: '" + clearedUrl + "'");
			request.setAttribute("real_url", clearedUrl);

			RequestDispatcher dispatcher = request.getRequestDispatcher(clearedUrl);
			dispatcher.forward(request, response);

			return false;
		}

//	Get dynamic page
		PagesEntity page = context.getData().getDynamicPage(context.getSession().getAppropriateEntity(request).getLanguage(), clearedUrl);
		if (page != null) {
			request.setAttribute("page", page);
		}

//	Menu
		request.setAttribute("menu", context.getData().getMenu().get(currentLanguage.getCode()));

		System.out.println("+++ END preHandle +++");

	    return true;
	}

	@Override
	public void postHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		ModelAndView model
	) throws Exception
	{
		AppContext context = AppContext.getInstance();

//	Languages
		request.setAttribute("langs", context.getData().getLanguages());

//  Alerts
		if (context.getAlert().isEnabled()) {
			request.setAttribute("alert", context.getAlert());
		}
	}
}