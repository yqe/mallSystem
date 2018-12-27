package com.zk.mall.service;


import com.zk.mall.entity.user;

import java.util.List;

public interface userService {
    boolean update(user user);

    boolean insert(user user);

    List<user> getAllUser();

    user getuserByEmail(String email);

    boolean login(String email, String password);
}
