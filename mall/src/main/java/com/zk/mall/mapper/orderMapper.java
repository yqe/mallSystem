package com.zk.mall.mapper;


import com.zk.mall.entity.order;
import com.zk.mall.mapper.provider.orderProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface orderMapper {

    @Select("SELECT * FROM goodsOrder WHERE id = #{id}")
    order getOrderById(int id);

    @Select("SELECT * FROM goodsOrder WHERE userId = #{userId} and state = #{state}")
    List<order> getOrderByUserId(@Param("userId")int userId,@Param("state")String state);

    @InsertProvider(type = orderProvider.class,method = "insert")
    Boolean insert(@Param("goodsOrder") order order);

    @UpdateProvider(type = orderProvider.class,method = "update")
    Boolean update(@Param("goodsOrder") order order);
}