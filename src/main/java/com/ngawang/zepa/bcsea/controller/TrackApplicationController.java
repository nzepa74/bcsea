package com.ngawang.zepa.bcsea.controller;

import com.ngawang.zepa.bcsea.service.TrackApplicationService;
import com.ngawang.zepa.helper.ResponseMessage;
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
@RequestMapping("/trackApplication")
public class TrackApplicationController {
    @Autowired
    private TrackApplicationService trackApplicationService;

    ResponseMessage responseMessage;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(ModelMap model) {
        return "bcsea/trackApplication";
    }

    @ResponseBody
    @RequestMapping(value = "/getTrackApplicationDetail", method = RequestMethod.GET)
    public ResponseMessage getTrackApplicationDetail(String applicationNo) {
        responseMessage = trackApplicationService.getTrackApplicationDetail(applicationNo);
        return responseMessage;
    }
}
