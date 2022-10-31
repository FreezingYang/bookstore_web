package com.service;

import com.entity.Cart;
import com.entity.CartItem;
import com.entity.User;

import java.util.List;

public interface CartService {
    // 查询当前用户的购物车
    List<CartItem> getCartItemList(User user);
    // 新增购物车项
    void addCartItem(CartItem cart);
    // 修改特定的购物车项
    void updateCartItem(Integer buyCount, Integer id);
    // 判断当前用户的购物车中是否有这本书的CartItem，有 -> update, 无 -> add
    void addOrUpdateCartItem(CartItem cartItem, Cart cart);
    // 删除指定用户的购物车项
    void deleteCartItem(Integer bookId, Integer userId);
    // 清空指定用户的购物车
    void emptyCartItem(User user);
}
