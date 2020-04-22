package com.ngawang.zepa.auth.dao;

import com.ngawang.zepa.auth.dto.UserDTO;
import com.ngawang.zepa.bcsea.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by N-Zepa on 25-Sep-19.
 */
@Repository
public class UserLoginDao {

    @Autowired
    SessionFactory sessionFactoryG2C;
    private Query hQuery;

    @Transactional(readOnly = true)
    public UserDTO login(String username) {
        Session session = sessionFactoryG2C.getCurrentSession();
        String query = "SELECT U.username AS username\n" +
                ",U.userPassword AS password \n" +
                ",userFullName AS fullName\n" +
                ",roleName AS roleName\n" +
                ",userGroupId AS userGroupId\n" +
                ",IS_ACTIVE AS userStatus\n" +
                "FROM becea_user U WHERE U.username =:username";
        hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(UserDTO.class))
                .setParameter("username", username);
        return (UserDTO) (hQuery.list().isEmpty() ? null : hQuery.list().get(0));
    }

//    @Transactional(readOnly = true)
//    public UserDTO login(String username) {
//        String sql = properties.getProperty("UserDao.getUserDetail");
//        org.hibernate.Query hQuery = hibernateQuery(sql, UserDTO.class);
//        hQuery.setParameter("username", username);
//        return hQuery.list().isEmpty() ? null : (UserDTO) hQuery.list().get(0);
//    }
}
