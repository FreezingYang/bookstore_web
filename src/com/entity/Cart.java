package com.entity;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private List<CartItem> cartItemList; // 购物车中购物项的集合，这个Map集合中的key是Book的id
    private BigDecimal totalMoney; // 购物车的总金额
    private Integer totalCount; // 购物车中购物项的数量

    public Cart() {
    }

    public Cart(List<CartItem> cartItemList, BigDecimal totalMoney, Integer totalCount) {
        this.cartItemList = cartItemList;
        this.totalMoney = totalMoney;
        this.totalCount = totalCount;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public BigDecimal getTotalMoney() {
        totalMoney = BigDecimal.valueOf(0.0);
        if(cartItemList!=null && cartItemList.size()>0){
            for (CartItem item : cartItemList) {
                BigDecimal price = BigDecimal.valueOf(item.getBook().getPrice());
                BigDecimal buyCount = BigDecimal.valueOf(item.getBuyCount());
                BigDecimal multiply = price.multiply(buyCount);
                totalMoney = totalMoney.add(multiply);
            }
        }
        return totalMoney;
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if (cartItemList!=null && cartItemList.size()>0){
            totalCount = cartItemList.size();
        }
        return totalCount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItemList=" + cartItemList +
                ", totalMoney=" + totalMoney +
                ", totalCount=" + totalCount +
                '}';
    }
}
