package com.zk.mall.controller;

import com.zk.mall.entity.*;
import com.zk.mall.service.goodsService;
import com.zk.mall.service.orderService;
import com.zk.mall.service.recommendService;
import com.zk.mall.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Controller
public class userController {
    @Autowired
    userService userService;
    @Autowired
    goodsService goodsService;
    @Autowired
    orderService orderService;
    @Autowired
    recommendService recommendService;


    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String indexpage(){
        return "index";
    }

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/userRegister",method = RequestMethod.GET)
    public String userRegister(){
        return "userRegister";
    }

    @RequestMapping(value="/userLogin",method = RequestMethod.POST)
    public String userLogin(Model model, HttpServletRequest request){
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        if(userService.getuserByEmail(email)==null){
            model.addAttribute("error", "邮箱不存在，请重新输入！");
            return "login";
        }

        if(userService.login(email, password)){
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            return "redirect:categories";
        }
        else{
            model.addAttribute("error", "密码错误，请重新登录！");
            return "login";
        }
    }

    @RequestMapping(value="/adduser",method = RequestMethod.POST)
    public String adduser(Model model,HttpServletRequest request){
        String email = request.getParameter("email");
        if(userService.getuserByEmail(email) != null){
            model.addAttribute("error","该邮箱已存在，请重新注册！");
            return "userRegister";
        }
        user user = new user();
        user.setEmail(request.getParameter("email"));
        user.setName(request.getParameter("name"));
        user.setPhone(request.getParameter("phone"));
        user.setPassword(request.getParameter("password"));
        userService.insert(user);
        user = userService.getuserByEmail(email);
        recommend recommend = new recommend(user.getUserId(),0,0,0,0);
        recommendService.insert(recommend);
        return "redirect:index";
    }

    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    public String userInfo(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        model.addAttribute("user",user);
        return "userInfoUpdate";
    }

    @RequestMapping(value = "/userInfoUpdate",method = RequestMethod.POST)
    public ModelAndView userInfoUpdate(HttpServletRequest request) {
        String email = request.getParameter("userEmail");
        user user = userService.getuserByEmail(email);
        user.setPhone(request.getParameter("phone"));
        user.setName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        userService.update(user);
        return new ModelAndView("redirect:/userInfo");
    }

    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    public String categories(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        model.addAttribute("user",user);
        return "categories";
    }

    @RequestMapping(value = "/goodsInfo/{type}",method = RequestMethod.GET)
    public String goodsInfo(@PathVariable("type")String type, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        model.addAttribute("user",user);
        List<goods> goodsList = goodsService.getGoodsListByType(type);
        model.addAttribute("goodsList",goodsList);
        model.addAttribute("type",type);
        return "goodsInfo";
    }

    @RequestMapping(value = "/addInterest/{id}",method = RequestMethod.GET)
    public String addInterest(@PathVariable("id")int id, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        model.addAttribute("user",user);
        String userInterest = user.getInterest();
        String[] interest;
        goods goods = goodsService.getGoodsById(id);
        if(userInterest == null||userInterest.equals(""))
            interest = new String[]{};
        else
            interest = userInterest.split(",");
        ArrayList<String> interestList = new ArrayList<>();
        Collections.addAll(interestList, interest);
        String interestId = String.valueOf(id);
        if(!interestList.contains(interestId))
            interestList.add(interestId);
        String interestStr = interestList.toString().substring(1,interestList.toString().length()-1);
        interestStr = interestStr.replace(" ","");
        user.setInterest(interestStr);
        userService.update(user);

        return "redirect:/goodsInfo/"+ URLEncoder.encode(goods.getType(), "utf-8");
    }

    @RequestMapping(value = "/deleteInterest/{id}",method = RequestMethod.GET)
    public String deleteInterest(@PathVariable("id")int id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        String userInterest = user.getInterest();
        String[] interest;
        if(userInterest == null||userInterest.equals(""))
            interest = new String[]{};
        else
            interest = userInterest.split(",");
        ArrayList<String> interestList = new ArrayList<>();
        Collections.addAll(interestList, interest);
        String interestId = String.valueOf(id);
        interestList.remove(interestId);
        String interestStr = interestList.toString().substring(1,interestList.toString().length()-1);
        interestStr = interestStr.replace(" ","");
        user.setInterest(interestStr);
        userService.update(user);
        List<goods> goodsList = new ArrayList<>();
        for (String anInterestList : interestList) {
            goodsList.add(goodsService.getGoodsById(Integer.valueOf(anInterestList)));
        }
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsList);
        return "interest";
    }

    @RequestMapping(value = "/interest",method = RequestMethod.GET)
    public String interest(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        String userInterest = user.getInterest();
        String[] interest;
        if(userInterest == null||userInterest.equals(""))
            interest = new String[]{};
        else
            interest = userInterest.split(",");
        List<goods> goodsList = new ArrayList<>();
        if (!(interest.length==0)){
            for (String goodsId : interest) {
                goodsList.add(goodsService.getGoodsById(Integer.valueOf(goodsId)));
            }
        }
        String type = recommendService.getRecommendType(user.getUserId());
        List<goods> recommendList = goodsService.getRecommendGoodsListByType(type);
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsList);
        model.addAttribute("recommendList",recommendList);
        return "interest";
    }

    @RequestMapping(value = "/setTime",method = RequestMethod.POST)
    public String setTime(@RequestBody timeSet timeSet, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        recommend recommend = recommendService.getRecommend(user.getUserId());
        recommend = setRecommendTime(recommend, timeSet.getTime(), timeSet.getType());
        recommendService.update(recommend);
        return "redirect:/categories";
    }

    public recommend setRecommendTime(recommend recommend, int time,String type ){
        switch (type){
            case "奶制品":recommend.setMilk(recommend.getMilk()+time);
                break;
            case "肉类":recommend.setMeat(recommend.getMeat()+time);
                break;
            case "酒":recommend.setWine(recommend.getWine()+time);
                break;
            case "毯子":recommend.setBlanket(recommend.getBlanket()+time);
                break;
        }
        return recommend;
    }

    @RequestMapping(value = "/generateOrder",method = RequestMethod.POST)
    public String generateOrder(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = bf.format(new Date());
        int id = Integer.valueOf(request.getParameter("id"));
        int num = Integer.valueOf(request.getParameter("num"));
        goods goods = goodsService.getGoodsById(id);
        order order = new order();
        order.setUserId(user.getUserId());
        order.setState("ongoing");
        order.setGoodsName(goods.getName());
        order.setNum(num);
        order.setPrice(num*goods.getPrice());
        order.setDate(date);
        orderService.insert(order);
        goods.setSellNum(goods.getSellNum()+num);
        goodsService.update(goods);
        List<order> orderList = orderService.getUserOrder(user.getUserId(),"ongoing");
        model.addAttribute("user",user);
        model.addAttribute("orderList",orderList);
        return "redirect:ongoingOrder";
    }


    @RequestMapping(value = "/ongoingOrder",method = RequestMethod.GET)
    public String ongoingOrder(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        List<order> orderList = orderService.getUserOrder(user.getUserId(),"ongoing");
        model.addAttribute("user",user);
        model.addAttribute("orderList",orderList);
        return "ongoingOrder";
    }

    @RequestMapping(value = "/orderUpdate/{id}",method = RequestMethod.GET)
    public String orderUpdate(@PathVariable("id")int id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user = userService.getuserByEmail(session.getAttribute("email").toString());
        order order = orderService.getOrderById(id);
        model.addAttribute("user",user);
        model.addAttribute("order",order);
        return "orderUpdate";
    }

    @RequestMapping(value = "/orderUpdateAction",method = RequestMethod.POST)
    public String orderUpdateAction(Model model, HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("orderId"));
        int num = Integer.valueOf(request.getParameter("orderNum"));
        String goodsName = request.getParameter("orderGoodsName");
        goods goods = goodsService.getGoodsByName(goodsName);
        order order = orderService.getOrderById(id);
        order.setNum(num);
        order.setPrice(goods.getPrice()*num);
        orderService.update(order);
        return "redirect:ongoingOrder";
    }

    @RequestMapping(value = "/finishedOrder",method = RequestMethod.GET)
    public String finishedOrder(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        List<order> orderList = orderService.getUserOrder(user.getUserId(),"finished");
        model.addAttribute("user",user);
        model.addAttribute("orderList",orderList);
        return "finishedOrder";
    }

    @RequestMapping(value = "/cancelOrder/{id}",method = RequestMethod.GET)
    public ModelAndView cancelOrder(@PathVariable("id")int id,Model model, HttpServletRequest request) {
        order order = orderService.getOrderById(id);
        order.setState("canceled");
        orderService.update(order);
        return new ModelAndView("redirect:/ongoingOrder");
    }

    @RequestMapping(value = "/firmOrder/{id}",method = RequestMethod.GET)
    public ModelAndView firmOrder(@PathVariable("id")int id,Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        order order = orderService.getOrderById(id);
        BigDecimal bg = new BigDecimal(user.getScore()+0.1*order.getPrice());
        double score = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        user.setScore(score);
        order.setState("finished");
        userService.update(user);
        orderService.update(order);
        return new ModelAndView("redirect:/ongoingOrder");
    }

    @RequestMapping(value = "/scoreExchange",method = RequestMethod.GET)
    public String scoreExchange(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        model.addAttribute("user",user);
        return "scoreExchange";
    }

    @RequestMapping(value = "/Exchange/{score}",method = RequestMethod.GET)
    public String Exchange(@PathVariable("score")double score,Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user user=userService.getuserByEmail(session.getAttribute("email").toString());
        if(user.getScore()<score){
            model.addAttribute("error", "积分不够，兑换失败！");
        }
        else{
            model.addAttribute("success", "兑换成功！");
            user.setScore(user.getScore()-score);
        }
        userService.update(user);
        model.addAttribute("user", user);
        return "scoreExchange";
    }

}
