ViewResultDao.getResult = CALL USP_BCSEA_GET_STD_RESULT(:indexNo,:classType)

ViewResultDao.getStudentDetail = SELECT DISTINCT \
                                D.STUDENT_NAME AS studentName \
                                ,D.INDEX_NO AS indexNo \
                                ,D.CID AS cidNo \
                                ,S.SCHOOL_NAME AS schoolName \
                                ,D.EXAM_YEAR AS examYear \
                                ,M.CLASSTYPE AS classType \
                                ,T.STREAM_NAME AS streamName \
                                ,D.BIRTH_DATE AS dob \
                                ,M.SPECIAL_GRADE AS supwGrade \
                                ,M.PASS_CERTIFICATE_AWARDED_TYPE_DESC AS resultRemarks \
                                FROM t_student_details D \
                                INNER JOIN t_school_master S ON D.SCHOOL_ID = S.SCHOOL_ID \
                                INNER JOIN t_stream_master T ON T.STREAM_ID = D.STREAM_ID \
                                INNER JOIN t_student_mark_details M ON M.INDEX_NUMBER = D.INDEX_NO \
                                WHERE D.INDEX_NO =:indexNo