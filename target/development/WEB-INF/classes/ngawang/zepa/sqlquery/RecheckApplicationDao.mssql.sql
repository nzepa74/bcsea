RecheckApplicationDao.getServiceActivityDuration = SELECT A.ACTIVE_FROM AS activeFrom, A.ACTIVE_TO AS activeTo \
                      FROM t_service_activity_duration A WHERE A.SERVICE_SL_NO =:serviceTypeId \
                      AND A.CLASS_TYPE =:classType AND A.EXAM_YEAR =:examYear AND A.ACTIVE_TAG = 'A'

RecheckApplicationDao.getRecheckCharge = SELECT S.CHARGE_AMOUNT AS amountCharge FROM t_service_charge S WHERE S.IS_ACTIVE ='Y' AND S.SERVICE_SL_NO =:serviceTypeId

RecheckApplicationDao.getStudentInfoByIndexNo = SELECT D.CID AS cidNo,D.INDEX_NO AS indexNo \
                    ,D.STUDENT_NAME AS studentName \
                    ,D.SCHOOL_ID AS schoolId,S.SCHOOL_NAME AS schoolName \
                    ,D.EXAM_YEAR AS examYear,D.MONTH AS examMonth \
                    ,STREAM_ID AS streamId, D.IS_ACTIVE AS isActive \
                    ,M.CLASSTYPE AS classType \
                    FROM t_student_details D \
                    INNER JOIN t_school_master S ON D.SCHOOL_ID = S.SCHOOL_ID \
                    INNER JOIN t_student_mark_details M ON M.INDEX_NUMBER = D.INDEX_NO \
                    WHERE D.INDEX_NO =:indexNo LIMIT 1

RecheckApplicationDao.getSubjectsByIndexNo = SELECT DISTINCT P.PAPER_ID AS paperId \
                    ,P.SUBJECT_ID AS subjectId,P.PAPER_NAME AS paperName \
                    ,S.SUBJECT_NAME AS subjectName FROM t_student_mark_details M \
                    INNER JOIN t_paper_master P ON M.SUBJECT_ID = P.SUBJECT_ID \
                    INNER JOIN t_subject_master S ON S.SUBJECT_ID = P.SUBJECT_ID \
                    WHERE M.INDEX_NUMBER =:indexNo

RecheckApplicationDao.getRecheckDetailId = SELECT R.RECHECK_DETAIL_ID FROM t_recheck_paper_details R ORDER BY R.RECHECK_DETAIL_ID DESC LIMIT 1

RecheckApplicationDao.getSubjectIdByPaperId = SELECT P.SUBJECT_ID FROM t_paper_master P WHERE P.PAPER_ID =:paperId

RecheckApplicationDao.getOldMarksByPaperId = SELECT M.MARKS_OBTAINED FROM t_student_mark_details M WHERE M.PAPER_ID =:paperId AND M.INDEX_NUMBER =:indexNo

RecheckApplicationDao.getDuplicateRecheckPaper = SELECT R.APPLICATION_NO FROM t_recheck_paper_details R WHERE R.INDEX_NO =:indexNo AND R.PAPER_ID =:paperId

RecheckApplicationDao.getPaperName = SELECT P.PAPER_NAME FROM t_paper_master P WHERE P.PAPER_ID =:paperId
