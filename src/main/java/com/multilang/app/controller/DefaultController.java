package com.multilang.app.controller;
 
import com.multilang.app.model.PagesEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DefaultController extends AbstractController
{
    @RequestMapping(
		value = "/", 
		method = RequestMethod.GET
	)
    public String index(Model model, HttpServletRequest request)
    {
        String text = this.context.getData().getText("index", this.context.getSession().getAppropriateEntity(request).getLanguage());
        model.addAttribute("text", text);
    	
        return "default/index";
    }

    @RequestMapping(
        value = "/static",
        method = RequestMethod.GET
    )
    public String staticPage(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        String realUrl = (String) request.getAttribute("real_url");
        if (realUrl != null) {
            PagesEntity page = this.context.getData().getStaticPage(this.context.getSession().getAppropriateEntity(request).getLanguage(), realUrl);
            if (page != null) {
                model.addAttribute("page", page);

                response.setStatus(200);
            }
        }

        return "default/static";
    }

    /**
     * Switching locale
     * @param model
     * @param lang
     * @return
     */
    @RequestMapping(
        value = "/locale",
        method = RequestMethod.GET
    )
    public String indexLocal(Model model, @RequestParam("lang") String lang)
    {
        System.out.println(lang);

        return "redirect:/" + lang;
    }
}