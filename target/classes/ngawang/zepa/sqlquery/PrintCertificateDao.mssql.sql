PrintCertificateDao.getCertificateList = SELECT A.APPLICATION_NO AS applicationNo \
                        ,S.SCHOOL_NAME AS schoolName \
                        ,CONCAT(A.FIRST_NAME, CONCAT(A.MIDDLE_NAME, A.LAST_NAME)) AS fullName \
                        ,A.CID AS cidNo \
                        ,A.INDEX_NUMBER AS indexNo \
                        ,M.DOCUMENT_NAME AS documentName \
                        ,M.DOCUMENT_ID AS documentId \
                        FROM t_application_details A \
                        INNER JOIN t_school_master S ON A.SCHOOL_ID =  S.SCHOOL_ID \
                        INNER JOIN t_applied_document D ON A.APPLICATION_NO = D.APPLICATION_NO \
                        INNER JOIN t_document_master M ON M.DOCUMENT_ID = D.DOCUMENT_TYPE_ID \
                        WHERE A.SERVICE_SL_NO =:serviceId \
                        AND A.STATUS_ID = 5 AND D.printed = 'N'

PrintCertificateDao.getLanguageProList = SELECT A.APPLICATION_NO AS applicationNo \
                      ,S.SCHOOL_NAME AS schoolName \
                      ,CONCAT(A.FIRST_NAME, CONCAT(A.MIDDLE_NAME, A.LAST_NAME)) AS fullName \
                      ,A.CID AS cidNo \
                      ,A.INDEX_NUMBER AS indexNo \
                      ,'Language Proficiency' AS documentName \
                      ,4 AS documentId \
                      FROM t_application_details A \
                      INNER JOIN t_school_master S ON A.SCHOOL_ID = S.SCHOOL_ID \
                      WHERE A.SERVICE_SL_NO =:serviceId \
                      AND A.STATUS_ID = 5 AND A.PRINTED = 'N'