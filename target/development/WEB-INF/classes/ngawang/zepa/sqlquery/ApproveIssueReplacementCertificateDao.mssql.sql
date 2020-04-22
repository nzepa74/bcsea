ApproveIssueReplacementCertificateDao.getAttachedFiles = SELECT D.Document_Id AS documentId \
                ,D.Application_Number  AS applicationNo \
                ,D.Document_Name AS documentName \
                ,D.Upload_URL AS uploadUrl FROM t_document_dtls D \
                WHERE D.Application_Number =:applicationNo

ApproveIssueReplacementCertificateDao.downloadFile = SELECT D.Document_Id AS documentId \
                ,D.Application_Number  AS applicationNo \
                ,D.Document_Name AS documentName \
                ,D.Upload_URL AS uploadUrl FROM t_document_dtls D \
                WHERE D.Document_Id =:documentId

ApproveIssueReplacementCertificateDao.getIndexNo = SELECT A.INDEX_NUMBER FROM t_application_details A WHERE A.APPLICATION_NO =:applicationNo

ApproveIssueReplacementCertificateDao.updateNameAndDob = UPDATE t_student_details SET STUDENT_NAME =:nameChange, BIRTH_DATE =:dobChange WHERE INDEX_NO =:indexNo

ApproveIssueReplacementCertificateDao.updateStudentName = UPDATE t_student_details SET STUDENT_NAME =:nameChange WHERE INDEX_NO =:indexNo"

ApproveIssueReplacementCertificateDao.updateStudentDob = UPDATE t_student_details SET BIRTH_DATE =:dobChange WHERE INDEX_NO =:indexNo