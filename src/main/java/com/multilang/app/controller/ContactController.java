package com.multilang.app.controller;

import com.multilang.app.model.PagesEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContactController extends AbstractController
{
    @RequestMapping(
		value = "/contact",
		method = RequestMethod.GET
	)
    public String index(Model model, HttpServletRequest request)
    {
//        String realUrl = (String) request.getAttribute("real_url");
//        if (realUrl != null) {
//            PagesEntity page = this.context.getData().getDynamicPage(this.context.getSession().getAppropriateEntity(request).getLanguage(), realUrl);
//            if (page != null) {
//                model.addAttribute("page", page);
//            }
//        }

        return "contact/index";
    }
}