package com.zk.mall.service.serviceImpl;

import com.zk.mall.entity.order;
import com.zk.mall.mapper.orderMapper;
import com.zk.mall.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderServiceImpl implements orderService {
    @Autowired
    orderMapper orderMapper;
    @Override
    public order getOrderById(int id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public List<order> getUserOrder(int userId, String state) {
        return orderMapper.getOrderByUserId(userId, state);
    }

    @Override
    public boolean update(order order) {
        return orderMapper.update(order);
    }

    @Override
    public boolean insert(order order) {
        return orderMapper.insert(order);
    }
}
