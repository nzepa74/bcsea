PaymentDetailDao.getPaymentList =  SELECT \
                                P.PAYMENT_DETAIL_ID AS paymentDetailId \
                                ,P.AMOUNT_CHARGE AS amountCharge \
                                ,P.APPLICATION_NO AS applicationNo \
                                ,A.SERVICE_SL_NO AS serviceSlNo \
                                ,S.SERVICE_NAME AS serviceName \
                                ,A.INDEX_NUMBER AS indexNo \
                                ,A.CID AS cidNo \
                                ,H.SCHOOL_NAME AS schoolName \
                                ,A.STATUS_ID AS statusId \
                                FROM t_application_details A INNER JOIN t_service_master S \
                                ON A.SERVICE_SL_NO = S.SERVICE_SL_NO INNER JOIN t_school_master H \
                                ON H.SCHOOL_ID = A.SCHOOL_ID INNER JOIN t_app_payment_details P \
                                ON P.APPLICATION_NO = A.APPLICATION_NO \
                                WHERE P.IS_PAID = 'N'

PaymentDetailDao.updatePaymentDetail = UPDATE t_app_payment_details SET PAYMENT_TYPE =:paymentType, RECEIPT_NO =:receiptNo, \
                                DEPOSITE_DATE =:depositDate ,IS_PAID =:isPaidStatus \
                                WHERE PAYMENT_DETAIL_ID =:paymentDetailId  AND APPLICATION_NO =:applicationNo