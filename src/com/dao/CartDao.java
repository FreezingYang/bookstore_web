package com.dao;

import com.entity.CartItem;
import com.entity.User;

import java.util.List;

public interface CartDao {
    // 查询当前用户的购物车，并设置book
    List<CartItem> getCartItemList(User user);
    // 新增购物车项
    void addCartItem(CartItem cart);
    // 修改特定的购物车项
    void updateCartItem(Integer buyCount, Integer id);
    // 删除指定用户的购物车项
    void deleteCartItem(Integer bookId, Integer userId);
    // 清空指定用户的购物车
    void emptyCartItem(User user);
}
