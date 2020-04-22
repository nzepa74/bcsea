TrackApplicationDao.getTrackApplicationDetail = SELECT \
                    T.SUBMISSION_DATE AS submissionDate \
                    ,S.SERVICE_NAME AS serviceName \
                    ,L.STATUS_NAME AS statusName \
                    FROM t_application_details T INNER JOIN t_service_master S \
                    ON S.SERVICE_SL_NO = T.SERVICE_SL_NO INNER JOIN t_status_lookup L \
                    ON L.STATUS_ID = T.STATUS_ID \
                    WHERE T.APPLICATION_NO =:applicationNo