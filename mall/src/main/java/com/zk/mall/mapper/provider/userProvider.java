package com.zk.mall.mapper.provider;

import com.zk.mall.entity.user;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class userProvider {
    private static final String TABLE_NAME="user";
    public String insert(Map<String,Object> para){
        user user = (user) para.get("user");
        if(user!=null){
            BEGIN();
            INSERT_INTO(TABLE_NAME);
            VALUES("name","#{user.name,javaType=String,jdbcType=VARCHAR}");
            VALUES("password","#{user.password,javaType=String,jdbcType=VARCHAR}");
            VALUES("email","#{user.email,javaType=String,jdbcType=VARCHAR}");
            VALUES("phone","#{user.phone,javaType=String,jdbcType=VARCHAR}");
            VALUES("score","#{user.score,javaType=Double,jdbcType=DOUBLE}");
            VALUES("interest","#{user.interest,javaType=String,jdbcType=VARCHAR}");
        }
        return SQL();

    }


    public String update(Map<String,Object> para){
        user user = (user) para.get("user");
        BEGIN();
        UPDATE(TABLE_NAME);
        SET("name = #{user.name,javaType=String,jdbcType=VARCHAR}");
        SET("password = #{user.password,javaType=String,jdbcType=VARCHAR}");
        SET("email = #{user.email,javaType=String,jdbcType=VARCHAR}");
        SET("phone = #{user.phone,javaType=String,jdbcType=VARCHAR}");
        SET("score = #{user.score,javaType=Double,jdbcType=DOUBLE}");
        SET("interest = #{user.interest,javaType=String,jdbcType=VARCHAR}");
        WHERE("userId = #{user.userId,javaType=Int,jdbcType=INTEGER}");
        return SQL();
    }
}
