package com.controller;

import com.entity.*;
import com.service.CartService;
import com.service.OrderService;
import com.service.impl.CartServiceImpl;
import com.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServlet extends ViewBaseServlet{
    private final OrderService orderService = new OrderServiceImpl();
    private final CartService cartService = new CartServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("operate").equals("addOrder")){
            this.addOrder(req, resp);
        }
        if (req.getParameter("operate").equals("findOrder")){
            this.findOrder(req, resp);
        }
        if (req.getParameter("operate").equals("findAll")){
            this.findAll(req, resp);
        }
        if (req.getParameter("operate").equals("findDetail")){
            this.findOrderDetail(req, resp);
        }
        if (req.getParameter("operate").equals("orderDetail")){
            this.orderDetail(req, resp);
        }
        if (req.getParameter("operate").equals("updateOrder")){
            this.updateOrder(req, resp);
        }
        if (req.getParameter("operate").equals("deleteOrder")){
            this.deleteOrder(req, resp);
        }
    }

    protected void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = user.getCart();

        List<CartItem> cartItemList = cart.getCartItemList();
        Integer buyCount = 0;
        String uid = testUid();

        for (CartItem item : cartItemList) {
            buyCount += item.getBuyCount();
        }
        Order order = new Order(uid, buyCount, cart.getTotalMoney(), user.getUserId(), 0);
        orderService.addOrder(order);

        for (CartItem item : cartItemList) {
            if (item.getSum() != 0){
                OrderDetail orderDetail = new OrderDetail(0, item.getBook().getBookId(), item.getBook().getImgPath(), item.getBook().getName(), item.getBuyCount(), BigDecimal.valueOf(item.getSum()), uid, 0);
                orderService.addOrderDetail(orderDetail);
            }
        }
        cartService.emptyCartItem(user);
        cartService.getCartItemList(user);
        req.setAttribute("uid",uid);
        processTemplate("/pages/cart/checkout", req, resp);
    }

    protected void findOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orderList = orderService.findUserOrder(user.getUserId());
        req.setAttribute("orderList", orderList);
        processTemplate("/pages/order/order", req, resp);
    }

    protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int pageNo = 1;
        String pageNoStr = req.getParameter("pageNo");
        if (pageNoStr != null){
            pageNo = Integer.parseInt(pageNoStr);
        }
        // 设置会话域
        HttpSession session = req.getSession();
        // pageNo 当前页面数字，设置到会话域中
        session.setAttribute("pageNo", pageNo);
        // 总数据条数
        int orderCount = orderService.findAllOrderCount();
        // 总页数 (总记录条数 + 显示条数 - 1)/显示条数
        int pageCount = (orderCount + 10 - 1)/10;
        session.setAttribute("pageCount", pageCount);
        // 页码
        List<Integer> pageList = new ArrayList<>();
        for (int i = 1; i < pageCount + 1; i++) {
            pageList.add(i);
        }
        session.setAttribute("pageList", pageList);
        // 分页查询
        List<Order> orderList = orderService.findAllOrder(pageNo);
        req.setAttribute("orderList", orderList);
        processTemplate("/pages/manager/order_manager", req, resp);
    }

    protected void findOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String orderId = req.getParameter("orderId");
        List<OrderDetail> orderDetail = orderService.findOrderDetail(orderId);
        req.setAttribute("orderDetail", orderDetail);
        processTemplate("/pages/manager/order_details", req, resp);
    }

    protected void orderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String orderId = req.getParameter("orderId");
        List<OrderDetail> orderDetail = orderService.findOrderDetail(orderId);
        req.setAttribute("orderDetail", orderDetail);
        processTemplate("/pages/order/order_details", req, resp);
    }

    protected void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String orderId = req.getParameter("orderId");
        String status = req.getParameter("orderStatus");
        orderService.updateStatus(Integer.valueOf(status), orderId);
        this.findAll(req, resp);
    }

    protected void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String orderId = req.getParameter("orderId");
        orderService.deleteOrder(orderId);
        this.findAll(req, resp);
    }

    //生成订单编号
    public static String testUid() {
        // 1.最大支持1-9个集群机器部署
        int machineId = 1;
        // 2.生成uuid的hashCode值
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        // 3.有可能是负数
        if(hashCodeV < 0) {
            hashCodeV = - hashCodeV;
        }
        // 4.结果
        String value =  machineId + String.format("%015d", hashCodeV);
        System.out.println(value);

        return value;
    }
}
