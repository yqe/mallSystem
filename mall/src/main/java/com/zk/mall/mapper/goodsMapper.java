package com.zk.mall.mapper;
import com.zk.mall.entity.goods;
import com.zk.mall.mapper.provider.goodsProvider;
import com.zk.mall.mapper.provider.orderProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface goodsMapper {

    @Select("SELECT * FROM goods WHERE id = #{id}")
    goods getGoodsById(int id);

    @Select("SELECT * FROM goods WHERE name = #{name}")
    goods getGoodsByName(String name);

    @Select("SELECT * FROM goods WHERE type = #{type}")
    List<goods> getGoodsListByType(String type);

    @Select("SELECT * FROM goods WHERE type = #{type} ORDER BY sellNum DESC LIMIT 10")
    List<goods> getRecommendGoodsListByType(String type);


    @UpdateProvider(type = goodsProvider.class,method = "update")
    Boolean update(@Param("goods") goods goods);

}
