package com.ngawang.zepa.bcsea.service;

import com.ngawang.zepa.bcsea.dao.ViewResultDao;
import com.ngawang.zepa.bcsea.dto.ResultDTO;
import com.ngawang.zepa.bcsea.dto.ResultResponseMessage;
import com.ngawang.zepa.bcsea.dto.StudentDetailDTO;
import com.ngawang.zepa.enumeration.SystemDataInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by USER on 8/22/2019.
 */
@Service
public class ViewResultService {
    @Autowired
    private ViewResultDao viewResultDao;

    public ResultResponseMessage getResult(String indexNo, Integer classType) {
        ResultResponseMessage resultResponseMessage = new ResultResponseMessage();
        StudentDetailDTO studentDetailDTO = viewResultDao.getStudentDetail(indexNo);

        List<ResultDTO> resultDTOs = viewResultDao.getResult(indexNo, classType);

        if (resultDTOs == null || studentDetailDTO == null) {
            resultResponseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value());
            return resultResponseMessage;
        }
        if (resultDTOs != null) {
            resultResponseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            resultResponseMessage.setResponseDTO(resultDTOs);
        }
        if (studentDetailDTO != null) {
            resultResponseMessage.setStudentName(studentDetailDTO.getStudentName());
            resultResponseMessage.setIndexNo(studentDetailDTO.getIndexNo());
            resultResponseMessage.setSchoolName(studentDetailDTO.getSchoolName());
            resultResponseMessage.setExamYear(studentDetailDTO.getExamYear());
            resultResponseMessage.setClassType(studentDetailDTO.getClassType());
            resultResponseMessage.setStreamName(studentDetailDTO.getStreamName());
            resultResponseMessage.setSupwGrade(studentDetailDTO.getSupwGrade());
            resultResponseMessage.setResultRemarks(studentDetailDTO.getResultRemarks());
            resultResponseMessage.setDob(studentDetailDTO.getDob());
            resultResponseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        }
        return resultResponseMessage;
    }

}
