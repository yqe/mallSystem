package com.zk.mall.service.serviceImpl;


import com.zk.mall.entity.admin;
import com.zk.mall.mapper.adminMapper;
import com.zk.mall.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminServiceImpl implements adminService {
    @Autowired
    adminMapper adminMapper;


    @Override
    public boolean login(String name, String password) {
        admin admin = adminMapper.getAdmin(name);
        if(admin.getPassword().equals(password))
            return true;
        else
            return false;
    }

    @Override
    public admin getAdmin(String name) {
        return adminMapper.getAdmin(name);
    }
}
