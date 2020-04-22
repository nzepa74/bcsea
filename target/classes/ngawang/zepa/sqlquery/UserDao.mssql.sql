UserDao.getUserDetail = SELECT U.username AS username \
                ,U.userPassword AS password \
                ,userFullName AS fullName \
                ,roleName AS roleName \
                ,userGroupId AS userGroupId \
                ,IS_ACTIVE AS userStatus \
                FROM becea_user U WHERE U.username =:username