package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.TrackApplicationDao;
import com.ngawang.zepa.bcsea.dto.ApplicationDetailDTO;
import com.ngawang.zepa.enumeration.SystemDataInt;
import com.ngawang.zepa.helper.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by USER on 8/22/2019.
 */
@Service
public class TrackApplicationService {
    @Autowired
    private TrackApplicationDao trackApplicationDao;

    public ResponseMessage getTrackApplicationDetail(String applicationNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationDetailDTO applicationDetailDTO = trackApplicationDao.getTrackApplicationDetail(applicationNo);
        if (applicationDetailDTO != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(applicationDetailDTO);
        }
        return responseMessage;
    }
}
