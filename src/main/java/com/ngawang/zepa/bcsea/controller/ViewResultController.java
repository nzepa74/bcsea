package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.dto.ResultResponseMessage;
import com.ngawang.zepa.bcsea.service.ViewResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by USER on 8/22/2019.
 */
@Controller
@RequestMapping("/viewResult")
public class ViewResultController {
    @Autowired
    private ViewResultService viewResultService;

    ResultResponseMessage resultResponseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        return "bcsea/viewResult";
    }

    @ResponseBody
    @RequestMapping(value = "/getResult", method = RequestMethod.GET)
    public ResultResponseMessage getResult(String indexNo, Integer classType) {
        resultResponseMessage = viewResultService.getResult(indexNo, classType);
        return resultResponseMessage;
    }
}
