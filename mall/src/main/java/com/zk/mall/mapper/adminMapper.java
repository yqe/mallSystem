package com.zk.mall.mapper;
import com.zk.mall.entity.admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface adminMapper {

    @Select("SELECT * FROM admin WHERE name = #{name}")
    admin getAdmin(String name);

}