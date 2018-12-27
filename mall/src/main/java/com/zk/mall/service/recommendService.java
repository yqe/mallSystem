package com.zk.mall.service;

import com.zk.mall.entity.recommend;

public interface recommendService {

    String getRecommendType(int userId);
    recommend getRecommend(int userId);
    boolean insert(recommend recommend);
    boolean update(recommend recommend);
}
