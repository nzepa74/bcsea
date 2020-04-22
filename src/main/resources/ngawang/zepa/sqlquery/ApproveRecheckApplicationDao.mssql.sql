ApproveRecheckApplicationDao.getRecheckPapers = SELECT \
                              P.RECHECK_DETAIL_ID AS recheckDetailId \
                              ,P.APPLICATION_NO AS applicationNo \
                              ,P.PAPER_ID AS paperId \
                              ,R.PAPER_NAME AS paperName \
                              ,P.SUBJECT_ID AS subjectId \
                              ,S.SUBJECT_NAME AS subjectName \
                              ,P.OLD_MARKS AS oldMarks \
                              FROM t_recheck_paper_details P \
                              INNER JOIN t_paper_master R ON R.PAPER_ID = P.PAPER_ID \
                              INNER JOIN t_subject_master S ON S.SUBJECT_ID = P.SUBJECT_ID \
                              WHERE P.APPLICATION_NO =:applicationNo \
                              ORDER BY S.SUBJECT_ID

ApproveRecheckApplicationDao.updateNewMarks = UPDATE t_recheck_paper_details SET NEW_MARKS =:newMarks \
                              WHERE RECHECK_DETAIL_ID =:recheckDetailId AND APPLICATION_NO =:applicationNo
