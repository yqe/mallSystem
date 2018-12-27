package com.zk.mall.mapper;

import com.zk.mall.entity.order;
import com.zk.mall.entity.recommend;
import com.zk.mall.mapper.provider.orderProvider;
import com.zk.mall.mapper.provider.recommendProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface recommendMapper {
    @Select("SELECT * FROM recommend WHERE userId = #{userId}")
    recommend getUserRecommend(int userId);

    @InsertProvider(type = recommendProvider.class,method = "insert")
    Boolean insert(@Param("recommend") recommend recommend);

    @UpdateProvider(type = recommendProvider.class,method = "update")
    Boolean update(@Param("recommend") recommend recommend);
}
