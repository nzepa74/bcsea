ReportsDao.getStatusList = SELECT S.STATUS_ID AS valueInteger, S.STATUS_NAME AS text FROM t_status_lookup S WHERE S.IS_ACTIVE = 'Y'

ReportsDao.getDuplicateOrReplacement = SELECT DISTINCT \
                        CONCAT(A.FIRST_NAME, CONCAT(A.MIDDLE_NAME, A.LAST_NAME)) AS fullName \
                        ,A.CID AS cidNo \
                        ,A.APPLICATION_NO AS applicationNo \
                        ,A.INDEX_NUMBER AS indexNo \
                        ,A.ACTION_TAKEN_BY AS actionTakenBy \
                        ,A.ACTION_TAKEN_DATE AS actionTakenDate \
                        FROM t_application_details A \
                        LEFT JOIN t_applied_document D ON A.APPLICATION_NO = D.APPLICATION_NO \
                        LEFT JOIN t_student_mark_details M ON M.INDEX_NUMBER = A.INDEX_NUMBER \
                        WHERE A.SERVICE_SL_NO =:serviceId \
                        AND (:documentId IS NULL OR D.DOCUMENT_TYPE_ID =:documentId) \
                        AND (:classType IS NULL OR M.CLASSTYPE =:classType) \
                        AND (A.SUBMISSION_DATE BETWEEN :fromDate AND :toDate) \
                        AND (:statusId IS NULL OR A.STATUS_ID =:statusId)

ReportsDao.getEnglishLanPro = SELECT DISTINCT CONCAT(A.FIRST_NAME, CONCAT(A.MIDDLE_NAME, A.LAST_NAME)) AS fullName \
                      ,A.CID AS cidNo \
                      ,A.APPLICATION_NO AS applicationNo \
                      ,A.INDEX_NUMBER AS indexNo \
                      ,A.ACTION_TAKEN_BY AS actionTakenBy \
                      ,A.ACTION_TAKEN_DATE AS actionTakenDate \
                      FROM t_application_details A \
                      WHERE A.SERVICE_SL_NO =:serviceId \
                      AND (A.SUBMISSION_DATE BETWEEN :fromDate AND :toDate) \
                      AND (:statusId IS NULL OR A.STATUS_ID =:statusId)

ReportsDao.getRecheck = SELECT DISTINCT \
                        CONCAT(A.FIRST_NAME, CONCAT(A.MIDDLE_NAME, A.LAST_NAME)) AS fullName \
                        ,A.CID AS cidNo \
                        ,A.APPLICATION_NO AS applicationNo \
                        ,A.INDEX_NUMBER AS indexNo \
                        ,A.ACTION_TAKEN_BY AS actionTakenBy \
                        ,A.ACTION_TAKEN_DATE AS actionTakenDate \
                        FROM t_application_details A \
                        LEFT JOIN t_student_mark_details M ON M.INDEX_NUMBER = A.INDEX_NUMBER \
                        WHERE A.SERVICE_SL_NO =:serviceId \
                        AND (:classType IS NULL OR M.CLASSTYPE =:classType) \
                        AND (A.SUBMISSION_DATE BETWEEN :fromDate AND :toDate) \
                        AND (:statusId IS NULL OR A.STATUS_ID =:statusId)
