package com.zk.mall.service;


import com.zk.mall.entity.admin;

public interface adminService {

    boolean login(String name, String password);

    admin getAdmin(String name);
}
