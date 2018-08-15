package com.itheima.lottery.controller;

import com.itheima.lottery.bean.*;
import com.itheima.lottery.service.OrderService;
import com.itheima.lottery.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/submit")
    public String submit(HttpSession session,Model model){
        //1.从session中获取cart对象
        Cart cart = (Cart) session.getAttribute("cart");

        //2.根据cart对象封装Order对象
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setOid(UUIDUtils.getId());
        //订单状态 : 0 未付款, 1:待开奖, 2:已中奖, 3:已兑奖, 4:未中奖
        order.setState(0);
        order.setTotal(cart.getTotal());
        // 订单属于哪个用户创建的
        User user = (User) session.getAttribute("user");
        order.setUid(user.getUid());

        //3.(循环)根据cart中的cartItem对象封装OrderItem对象
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setRed(cartItem.getRed());
            orderItem.setBlue(cartItem.getBlue());
            orderItem.setCode("2018020");
            orderItem.setCount(cartItem.getCount());
            orderItem.setItemid(UUIDUtils.getId());
            orderItem.setOid(order.getOid());

            orderItems.add(orderItem);
        }

        //4.将所有的OrderItem封装到Order对象中
        order.setOrderItems(orderItems);

        //5.调用业务保存订单对象
        orderService.save(order);
        //6.生成响应
        model.addAttribute("order",order);
        return "order_detail";
    }

    @GetMapping("/mylist")
    public String mylist(HttpSession session,Model model){
        //1.获取用户的uid
        User user = (User) session.getAttribute("user");
        String uid = user.getUid();
        //2.调用业务查询当前用户所有的订单
        List<Order> orders = orderService.findByUid(uid);
        //3.生成响应
        model.addAttribute("orders",orders);
        return "order_list";
    }
}
