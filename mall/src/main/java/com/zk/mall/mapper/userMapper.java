package com.zk.mall.mapper;


import com.zk.mall.entity.user;
import com.zk.mall.mapper.provider.userProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface userMapper {

    @Select("SELECT * FROM user WHERE userId = #{userId}")
    user getUserById(int userId);

    @Select("SELECT * FROM user WHERE email = #{email}")
    user getUserByEmail(String email);

    @Select("SELECT * FROM user ")
    List<user> getAlluser();

    @InsertProvider(type = userProvider.class,method = "insert")
    Boolean insert(@Param("user") user user);

    @UpdateProvider(type = userProvider.class,method = "update")
    Boolean update(@Param("user") user user);
}