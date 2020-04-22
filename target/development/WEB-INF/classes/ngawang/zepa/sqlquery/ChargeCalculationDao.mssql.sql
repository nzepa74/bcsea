ChargeCalculationDao.getDuplicateOrReplacementDocCharge = CALL USP_BCSEA_DOC_CHARGE_CALCULATION (:serviceId ,:indexNo ,:documentId)

ChargeCalculationDao.getEnglishLanProCharge = CALL USP_BCSEA_ENG_LAN_CHARGE_CALCULATION (:serviceId ,:cidNo)

ChargeCalculationDao.isValidCid = SELECT A.CID FROM t_application_details A WHERE A.CID =:cidNo LIMIT 1