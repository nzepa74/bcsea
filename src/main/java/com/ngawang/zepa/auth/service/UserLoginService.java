package com.ngawang.zepa.auth.service;

import com.ngawang.zepa.auth.dao.UserLoginDao;
import com.ngawang.zepa.auth.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by N-Zepa on 25-Sep-19.
 */
@Service
public class UserLoginService {
    @Autowired
    private UserLoginDao userLoginDao;

    public UserDTO login(String username) {
        return userLoginDao.login(username);
    }
}
