ServiceActivityDurationDao.getServiceActivityDurationList = SELECT D.INDEX_NO AS autoIndex \
                          ,D.SERVICE_SL_NO AS serviceId \
                          ,S.SERVICE_NAME AS serviceName \
                          ,D.CLASS_TYPE AS classType \
                          ,D.EXAM_YEAR AS examYear \
                          ,D.ACTIVE_FROM AS activeFrom \
                          ,D.ACTIVE_TO AS activeTo \
                          ,D.ACTIVE_TAG AS statusTag \
                          ,IF(D.ACTIVE_TAG = 'A','Active','Inactive') AS statusTagName \
                          FROM t_service_activity_duration D  INNER JOIN t_service_master S \
                          ON S.SERVICE_SL_NO = D.SERVICE_SL_NO ORDER BY D.CLASS_TYPE, D.EXAM_YEAR DESC

ServiceActivityDurationDao.isExistSave = SELECT D.SERVICE_SL_NO FROM t_service_activity_duration D \
                          WHERE D.CLASS_TYPE =:classType AND D.EXAM_YEAR =:examYear LIMIT 1

ServiceActivityDurationDao.isExistUpdate = SELECT D.SERVICE_SL_NO FROM t_service_activity_duration D \
                          WHERE D.CLASS_TYPE =:classType AND D.EXAM_YEAR =:examYear AND INDEX_NO !=:autoIndex LIMIT 1

ServiceActivityDurationDao.getAutoIndex = SELECT D.INDEX_NO FROM t_service_activity_duration D ORDER BY D.INDEX_NO DESC LIMIT 1
