package com.service.impl;

import com.dao.CartDao;
import com.dao.impl.CartDaoImpl;
import com.entity.Cart;
import com.entity.CartItem;
import com.entity.User;
import com.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private final CartDao cartDao = new CartDaoImpl();

    @Override
    public List<CartItem> getCartItemList(User user) {
        return cartDao.getCartItemList(user);
    }

    @Override
    public void addCartItem(CartItem cart) {
        cartDao.addCartItem(cart);
    }

    @Override
    public void updateCartItem(Integer buyCount, Integer id) {
        cartDao.updateCartItem(buyCount, id);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        // 判断当前用户的购物车中是否有这本书的CartItem，有 -> update, 无 -> add
        if(cart!=null){
            List<CartItem> cartItemList = cart.getCartItemList();
            for (CartItem item : cartItemList) {
                if (item.getBookId() == cartItem.getBookId()){
                    item.setBuyCount(item.getBuyCount() + 1);
                    updateCartItem(item.getBuyCount(), item.getId());
                    return;
                }
            }
            addCartItem(cartItem);
        }else { // 连购物车都没有的情况
            addCartItem(cartItem);
        }
    }

    @Override
    public void deleteCartItem(Integer bookId, Integer userId) {
        cartDao.deleteCartItem(bookId, userId);
    }

    @Override
    public void emptyCartItem(User user) {
        cartDao.emptyCartItem(user);
    }
}
