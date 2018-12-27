package com.zk.mall.service.serviceImpl;

import com.zk.mall.entity.goods;
import com.zk.mall.mapper.goodsMapper;
import com.zk.mall.service.goodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class goodsServiceImpl implements goodsService {
    @Autowired
    goodsMapper goodsMapper;
    @Override
    public goods getGoodsById(int id){
        return goodsMapper.getGoodsById(id);
    }

    @Override
    public goods getGoodsByName(String name) {
        return goodsMapper.getGoodsByName(name);
    }

    @Override
    public List<goods> getGoodsListByType(String type) {
        return goodsMapper.getGoodsListByType(type);
    }

    @Override
    public List<goods> getRecommendGoodsListByType(String type) {
        return goodsMapper.getRecommendGoodsListByType(type);
    }

    @Override
    public boolean update(goods goods) {
        return goodsMapper.update(goods);
    }
}
