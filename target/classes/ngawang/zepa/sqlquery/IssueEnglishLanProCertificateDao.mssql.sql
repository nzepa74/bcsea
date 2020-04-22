IssueEnglishLanProCertificateDao.getEnglishMarkByIndexNo = CALL USP_BCSEA_GET_ENGLISH_MARKS(:indexNo)

IssueEnglishLanProCertificateDao.getDuplicateApplicationNo = SELECT A.APPLICATION_NO FROM t_application_details A \
                            WHERE A.CID =:cidNo AND A.INDEX_NUMBER =:indexNo \
                            AND A.SERVICE_SL_NO =:globalServiceTypeId AND A.PRINTED ='N' LIMIT 1
