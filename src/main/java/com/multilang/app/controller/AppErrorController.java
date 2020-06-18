package com.multilang.app.controller;

import com.multilang.app.model.PagesEntity;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AppErrorController extends AbstractController implements ErrorController
{
    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request)
    {
//  Check static page
        String realUrl = (String) request.getAttribute("real_url");
        if (realUrl != null) {
            PagesEntity page = this.context.getData().getStaticPage(this.context.getSession().getAppropriateEntity(request).getLanguage(), realUrl);
            if (page != null) {
                return "forward:/static";
            }
        }

//  Error page
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("message", "Page not found");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("message", "Internal server error");
            }
        }

        return "default/error";
    }

    @Override
    public String getErrorPath()
    {
        return "/default/error";
    }
}
