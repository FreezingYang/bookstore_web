package com.controller;

import com.entity.CartItem;
import com.entity.User;
import com.service.CartService;
import com.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartServlet extends ViewBaseServlet{
    private final CartService cartService = new CartServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("operate").equals("getCartItem")){
            processTemplate("pages/cart/cart", req, resp);
        }
        if (req.getParameter("operate").equals("addCart")){
            this.addCart(req, resp);
        }
        if (req.getParameter("operate").equals("updateCart")){
            this.updateCart(req, resp);
        }
        if (req.getParameter("operate").equals("deleteCart")){
            this.deleteCart(req, resp);
        }
        if (req.getParameter("operate").equals("emptyCart")){
            this.emptyCart(req, resp);
        }

    }

    protected void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        CartItem cartItem = new CartItem(Integer.valueOf(bookId), 1, user.getUserId());
        cartService.addOrUpdateCartItem(cartItem, user.getCart());
        cartService.getCartItemList(user);
        resp.sendRedirect("index.do");
    }

    protected void updateCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String buyCount = req.getParameter("buyCount");
        cartService.updateCartItem(Integer.valueOf(buyCount), Integer.valueOf(id));
        resp.sendRedirect("cart?operate=getCartItem");
    }

    protected void deleteCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        cartService.deleteCartItem(Integer.valueOf(bookId), user.getUserId());
        resp.sendRedirect("cart?operate=getCartItem");
    }

    protected void emptyCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        cartService.emptyCartItem(user);
        cartService.getCartItemList(user);
        resp.sendRedirect("cart?operate=getCartItem");
    }


}
