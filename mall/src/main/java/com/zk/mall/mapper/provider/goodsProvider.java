package com.zk.mall.mapper.provider;

import com.zk.mall.entity.goods;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;

public class goodsProvider {
    private static final String TABLE_NAME="goods";
    public String update(Map<String,Object> para){
        goods goods = (goods) para.get("goods");
        BEGIN();
        UPDATE(TABLE_NAME);
        SET("name = #{goods.name,javaType=String,jdbcType=VARCHAR}");
        SET("type = #{goods.type,javaType=String,jdbcType=VARCHAR}");
        SET("sellNum = #{goods.sellNum,javaType=Int,jdbcType=INTEGER}");
        SET("price = #{goods.price,javaType=Double,jdbcType=DOUBLE}");
        WHERE("id = #{goods.id,javaType=Int,jdbcType=INTEGER}");
        return SQL();
    }
}
