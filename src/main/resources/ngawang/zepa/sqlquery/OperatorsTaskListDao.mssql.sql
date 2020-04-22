OperatorsTaskListDao.getOperatorsTaskList = SELECT \
                          A.APPLICATION_NO AS applicationNo \
                          ,A.SERVICE_SL_NO AS serviceSlNo \
                          ,S.SERVICE_NAME AS serviceName \
                          ,A.INDEX_NUMBER AS indexNo \
                          ,A.MOB_NO AS mobileNo \
                          ,A.STATUS_ID AS statusId \
                          ,A.SUBMISSION_DATE AS submissionDate \
                          FROM t_application_details A INNER JOIN t_service_master S \
                          ON A.SERVICE_SL_NO = S.SERVICE_SL_NO WHERE A.STATUS_ID = 1

OperatorsTaskListDao.getClaimedTaskList = SELECT DISTINCT \
                          A.APPLICATION_NO AS applicationNo \
                          ,A.SERVICE_SL_NO AS serviceSlNo \
                          ,S.SERVICE_NAME AS serviceName \
                          ,A.INDEX_NUMBER AS indexNo \
                          ,A.MOB_NO AS mobileNo \
                          ,A.STATUS_ID AS statusId \
                          ,A.SUBMISSION_DATE AS submissionDate \
                          FROM t_application_details A INNER JOIN t_service_master S \
                          ON A.SERVICE_SL_NO = S.SERVICE_SL_NO INNER JOIN t_task_dtls T \
                          ON T.Application_Id = A.APPLICATION_NO \
                          WHERE A.STATUS_ID = 2 AND T.Owner =:owner
