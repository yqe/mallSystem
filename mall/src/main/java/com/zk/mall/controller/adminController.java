package com.zk.mall.controller;

import com.zk.mall.entity.admin;
import com.zk.mall.entity.goods;
import com.zk.mall.entity.order;
import com.zk.mall.entity.user;
import com.zk.mall.service.adminService;
import com.zk.mall.service.orderService;
import com.zk.mall.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class adminController {

    @Autowired
    adminService adminService;
    @Autowired
    userService userService;
    @Autowired
    orderService orderService;

    @RequestMapping(value="/adminLogin",method = RequestMethod.POST)
    public String adminLogin(Model model, HttpServletRequest request){
        String name = request.getParameter("adminName");
        String password = request.getParameter("adminPassword");
        if(adminService.login(name, password)){
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            return "redirect:checkUser";
        }
        else{
            model.addAttribute("error", "密码错误，请重新登录！");
            return "login";
        }
    }

    @RequestMapping(value = "/checkUser",method = RequestMethod.GET)
    public String checkUser(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        admin admin=adminService.getAdmin(session.getAttribute("name").toString());
        List<user> userList = userService.getAllUser();
        model.addAttribute("admin",admin);
        model.addAttribute("userList",userList);
        return "checkUser";
    }

    @RequestMapping(value = "/checkUserOrder/{email}",method = RequestMethod.GET)
    public String checkUser(@PathVariable("email")String email , Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        admin admin=adminService.getAdmin(session.getAttribute("name").toString());
        user user = userService.getuserByEmail(email);
        List<order> ongoingOrderList = orderService.getUserOrder(user.getUserId(),"ongoing");
        List<order> canceledOrderList = orderService.getUserOrder(user.getUserId(),"canceled");
        List<order> finishedOrderList = orderService.getUserOrder(user.getUserId(),"finished");
        model.addAttribute("admin",admin);
        model.addAttribute("user",user);
        model.addAttribute("ongoingOrderList",ongoingOrderList);
        model.addAttribute("canceledOrderList",canceledOrderList);
        model.addAttribute("finishedOrderList",finishedOrderList);
        return "checkUserOrder";
    }

}
