package com.zk.mall.mapper.provider;

import com.zk.mall.entity.recommend;
import com.zk.mall.entity.recommend;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class recommendProvider {
    private static final String TABLE_NAME="recommend";
    public String insert(Map<String,Object> para){
        recommend recommend = (recommend) para.get("recommend");
        if(recommend!=null){
            BEGIN();
            INSERT_INTO(TABLE_NAME);
            VALUES("userId","#{recommend.userId,javaType=int,jdbcType=INTEGER}");
            VALUES("milk","#{recommend.milk,javaType=int,jdbcType=INTEGER}");
            VALUES("meat","#{recommend.meat,javaType=int,jdbcType=INTEGER}");
            VALUES("wine","#{recommend.wine,javaType=int,jdbcType=INTEGER}");
            VALUES("blanket","#{recommend.blanket,javaType=int,jdbcType=INTEGER}");
        }
        return SQL();

    }


    public String update(Map<String,Object> para){
        recommend recommend = (recommend) para.get("recommend");
        BEGIN();
        UPDATE(TABLE_NAME);
        SET("milk = #{recommend.milk,javaType=int,jdbcType=INTEGER}");
        SET("meat = #{recommend.meat,javaType=int,jdbcType=INTEGER}");
        SET("wine = #{recommend.wine,javaType=int,jdbcType=INTEGER}");
        SET("blanket = #{recommend.blanket,javaType=int,jdbcType=INTEGER}");
        WHERE("userId = #{recommend.userId,javaType=Int,jdbcType=INTEGER}");
        return SQL();
    }
}
