package com.ngawang.zepa.auth.controller;

import com.ngawang.zepa.auth.dto.UserDTO;
import com.ngawang.zepa.helper.CurrentUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
//@RequestMapping(value = "")
@PreAuthorize("isAuthenticated()")
public class HomeController {
    /**
     * home controller
     *
     * @param request        request
     * @param response       response
     * @param authentication authentication
     * @return ModelAndView
     */
    @RequestMapping(value = {"/", "home"})
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        UserDTO userLoginDTO = (UserDTO) authentication.getPrincipal();

        CurrentUser currentUser = new CurrentUser();
        currentUser.setUsername(userLoginDTO.getUsername());
        currentUser.setUserFullName(userLoginDTO.getFullName());

        request.getSession().setAttribute("currentUser", currentUser);
        modelAndView.setViewName("home");
        return modelAndView;
    }
}