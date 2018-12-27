package com.zk.mall.service;

import com.zk.mall.entity.order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface orderService {
    order getOrderById(int id);
    List<order> getUserOrder(int userId,String state);
    boolean update(order order);
    boolean insert(order order);
}
