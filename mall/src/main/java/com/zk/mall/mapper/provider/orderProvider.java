package com.zk.mall.mapper.provider;

import com.zk.mall.entity.order;
import com.zk.mall.entity.user;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class orderProvider {
    private static final String TABLE_NAME="goodsOrder";
    public String insert(Map<String,Object> para){
        order goodsOrder = (order) para.get("goodsOrder");
        if(goodsOrder!=null){
            BEGIN();
            INSERT_INTO(TABLE_NAME);
            VALUES("userId","#{goodsOrder.userId,javaType=Int,jdbcType=INTEGER}");
            VALUES("goodsName","#{goodsOrder.goodsName,javaType=String,jdbcType=VARCHAR}");
            VALUES("num","#{goodsOrder.num,javaType=Int,jdbcType=INTEGER}");
            VALUES("price","#{goodsOrder.price,javaType=Double,jdbcType=DOUBLE}");
            VALUES("date","#{goodsOrder.date,javaType=String,jdbcType=VARCHAR}");
            VALUES("state","#{goodsOrder.state,javaType=String,jdbcType=VARCHAR}");
        }
        return SQL();

    }


    public String update(Map<String,Object> para){
        order goodsOrder = (order) para.get("goodsOrder");
        BEGIN();
        UPDATE(TABLE_NAME);
        SET("userId = #{goodsOrder.userId,javaType=Int,jdbcType=INTEGER}");
        SET("goodsName = #{goodsOrder.goodsName,javaType=String,jdbcType=VARCHAR}");
        SET("state = #{goodsOrder.state,javaType=String,jdbcType=VARCHAR}");
        SET("num = #{goodsOrder.num,javaType=Int,jdbcType=INTEGER}");
        SET("price = #{goodsOrder.price,javaType=Double,jdbcType=DOUBLE}");
        SET("date = #{goodsOrder.date,javaType=String,jdbcType=VARCHAR}");
        WHERE("id = #{goodsOrder.id,javaType=Int,jdbcType=INTEGER}");
        return SQL();
    }
}
