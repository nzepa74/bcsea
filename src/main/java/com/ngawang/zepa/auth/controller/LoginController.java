package com.ngawang.zepa.auth.controller;

import com.ngawang.zepa.auth.dto.UserDTO;
import com.ngawang.zepa.enumeration.LoginErrorCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by N-Zepa on 31-Jan-19.
 */

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    //    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String login(@RequestParam(value = "error", required = false) String error,
                        HttpServletRequest request, Model model) {

        if (error != null) {
            model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            return "home";
        }
    }

    /**
     * logout loader
     *
     * @return ModelAndView
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/home";
    }

    /**
     * authentication processing path
     *
     * @return ModelAndView
     */
    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ModelAndView auth() {

        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        return model;
    }

    /**
     * access denied path
     *
     * @param request request
     * @return ModelAndView
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView model = new ModelAndView();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDTO userLogin = (UserDTO) auth.getPrincipal();
            model.addObject("username", userLogin.getFullName());
        }
        model.setViewName("403");
        return model;
    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        if (exception != null) {
            String message = exception.getMessage();

            if (message.equals(LoginErrorCode.FAILED.getCode()) || message.equals(LoginErrorCode.LOCKED.getCode())) {
                return message;
            } else {
                return LoginErrorCode.MAX_SESSION.getCode();
            }
        } else {
            return null;
        }
    }
}
