package com.zk.mall.service.serviceImpl;

import com.zk.mall.entity.recommend;
import com.zk.mall.mapper.recommendMapper;
import com.zk.mall.service.recommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class recommendServiceImpl implements recommendService {

    @Autowired
    recommendMapper recommendMapper;
    @Override
    public String getRecommendType(int userId) {
        recommend recommend = recommendMapper.getUserRecommend(userId);
        Map<String,Integer> recommendMap = new HashMap<>();
        recommendMap.put("奶制品",recommend.getMilk());
        recommendMap.put("肉类",recommend.getMeat());
        recommendMap.put("酒",recommend.getWine());
        recommendMap.put("毯子",recommend.getBlanket());
        List<Map.Entry<String,Integer>> list = new ArrayList(recommendMap.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));
        return list.get(list.size()-1).getKey();
    }

    @Override
    public recommend getRecommend(int userId) {
        return recommendMapper.getUserRecommend(userId);
    }

    @Override
    public boolean insert(recommend recommend) {
        return recommendMapper.insert(recommend);
    }

    @Override
    public boolean update(recommend recommend) {
        return recommendMapper.update(recommend);
    }
}
