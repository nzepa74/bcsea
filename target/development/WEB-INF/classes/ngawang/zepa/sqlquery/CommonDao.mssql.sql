CommonDao.getDocumentList = SELECT D.DOCUMENT_ID AS valueInteger,D.DOCUMENT_NAME AS text FROM t_document_master D WHERE D.IS_ACTIVE = 'Y'

CommonDao.getAppliedDocumentList =  SELECT DISTINCT D.DOCUMENT_TYPE_ID AS valueInteger \
                  ,M.DOCUMENT_NAME AS text FROM t_applied_document D \
                  INNER JOIN t_document_master M ON D.DOCUMENT_TYPE_ID = M.DOCUMENT_ID \
                  WHERE D.APPLICATION_NO =:applicationNo

CommonDao.getApplicationSerial = SELECT RIGHT(A.APPLICATION_NO,5) FROM t_application_details A \
                  WHERE LEFT(A.APPLICATION_NO,3) =:globalServiceTypeId \
                  AND SUBSTR(A.APPLICATION_NO,5,2) =:applicationYear \
                  AND SUBSTR(A.APPLICATION_NO,7,2) =:applicationMonth \
                  AND SUBSTR(A.APPLICATION_NO,9,2) =:applicationDay \
                  AND SUBSTR(A.APPLICATION_NO,11,2) =:applicationType \
                  ORDER BY A.APPLICATION_NO DESC LIMIT 1

CommonDao.getStudentDetailsByIndexNo = SELECT D.INDEX_NO AS indexNo,D.CID AS cidNo, D.STUDENT_NAME AS studentName \
                  ,D.SCHOOL_ID AS schoolId,S.SCHOOL_NAME AS schoolName \
                  ,D.EXAM_YEAR AS examYear,D.MONTH AS examMonth  \
                  ,STREAM_ID AS streamId, D.IS_ACTIVE AS isActive \
                  FROM t_student_details D \
                  INNER JOIN t_school_master S ON D.SCHOOL_ID = S.SCHOOL_ID \
                  WHERE D.INDEX_NO =:indexNo

CommonDao.getCitizenName = SELECT D.CID AS cidNo, D.STUDENT_NAME AS studentName FROM t_student_details D WHERE D.CID =:cidNo

CommonDao.getAppliedDocumentId = SELECT ID FROM t_applied_document D ORDER BY D.ID DESC LIMIT 1

CommonDao.getChargeByDocumentId = SELECT C.CHARGE_AMOUNT FROM t_service_charge C \
                  WHERE (:documentId IS NULL OR C.DOCUMENT_ID =:documentId) AND C.SERVICE_SL_NO =:serviceId LIMIT 1

CommonDao.getWorkInstanceSerial = SELECT CAST(RIGHT(W.Workflow_instance_Id,8) AS INT) FROM t_workflow_dtls W ORDER BY W.Workflow_instance_Id DESC LIMIT 1

CommonDao.getTaskInstanceSerial = SELECT CAST(RIGHT(T.Task_instance_Id,8) AS INT) FROM t_task_dtls T ORDER BY T.Task_instance_Id DESC LIMIT 1

CommonDao.getLastPaymentDetailId = SELECT D.PAYMENT_DETAIL_ID FROM t_app_payment_details D ORDER BY D.PAYMENT_DETAIL_ID DESC LIMIT 1

CommonDao.getApplicationDetail = SELECT A.APPLICATION_NO AS applicationNo \
                  ,A.SERVICE_SL_NO AS serviceSlNo \
                  ,A.SUBMISSION_DATE AS submissionDate \
                  ,A.SUBMITTED_BY AS submittedBy \
                  ,A.ACTION_TAKEN_BY AS actionTakenBy \
                  ,A.ACTION_TAKEN_DATE AS actionTakenDate \
                  ,A.CID AS cidNo \
                  ,A.BIRTH_DATE AS birthDate \
                  ,CONCAT(A.FIRST_NAME, CONCAT(A.MIDDLE_NAME, A.LAST_NAME)) AS fullName \
                  ,A.FIRST_NAME AS firstName \
                  ,A.MIDDLE_NAME AS middleName \
                  ,A.LAST_NAME AS lastName \
                  ,A.GUARDIAN_NAME AS guardianName \
                  ,A.CONTACT_GUARDIAN_NAME AS fatherName \
                  ,A.CONTACT_ADDRESS AS address \
                  ,A.MOB_NO AS mobileNo \
                  ,A.EMAIL_ID AS email \
                  ,A.DOC_IS_COLLECTED AS documentCollection \
                  ,A.OLD_DOC_PROB AS reasonWithOriginalDoc \
                  ,A.CHANGES_REQUIRED AS changesRequired \
                  ,A.OLD_IS_RETURNED AS isOldDocReturned \
                  ,A.IS_EMPLOYED AS isEmployed \
                  ,A.AGENCY_NAME AS agencyName \
                  ,A.DESIGNATION AS designation \
                  ,A.LAST_EXAM_MONTH AS lastExamMonth \
                  ,A.LAST_EXAM_YEAR AS lastExamYear \
                  ,A.LAST_SCHOOL_NAME AS lastSchoolName \
                  ,A.PURPOSE AS purpose \
                  ,A.INDEX_NUMBER AS indexNo \
                  ,A.EXAM_MONTH AS examMonth \
                  ,A.EXAM_YEAR AS examYear \
                  ,A.SCHOOL_ID AS schoolId \
                  ,A.REMARKS AS remarks \
                  ,A.PRINTED AS isPrinted \
                  ,A.PAYMENT_MADE AS paymentMade \
                  ,A.STATUS_ID AS statusId \
                  ,A.COLLECTED_BY AS collectedBy \
                  ,A.REJECT_REASON_ID AS rejectReasonId \
                  FROM t_application_details A WHERE A.APPLICATION_NO =:applicationNo

CommonDao.getChargedApplied = CALL USP_BCSEA_GET_AMOUNT_CHARGED(:applicationNo)

CommonDao.getRejectReasons = SELECT R.REJECT_REASON_ID AS valueInteger, R.REASON AS text FROM t_reject_reasons R WHERE R.IS_ACTIVE ='Y'

CommonDao.getRejectReasonByRejectReasonId = SELECT R.REASON FROM t_reject_reasons R WHERE R.REJECT_REASON_ID =:rejectReasonId

CommonDao.validateDuplicateDocument = CALL USP_BCSEA_CHECK_DUPLICATE_DOC(:cidNo,:indexNo,:serviceTypeId,:documentTypeId)

CommonDao.getDocumentName = SELECT D.DOCUMENT_NAME FROM t_document_master D WHERE D.DOCUMENT_ID =:documentTypeId

CommonDao.updatePrintStatus = UPDATE t_applied_document SET printed ='Y' WHERE APPLICATION_NO =:applicationNo AND DOCUMENT_TYPE_ID =:documentId