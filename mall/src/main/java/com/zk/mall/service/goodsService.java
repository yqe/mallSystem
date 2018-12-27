package com.zk.mall.service;

import com.zk.mall.entity.goods;

import java.util.List;

public interface goodsService {
    goods getGoodsById(int id);
    goods getGoodsByName(String name);
    List<goods> getGoodsListByType(String type);
    List<goods> getRecommendGoodsListByType(String type);
    boolean update(goods goods);
}
