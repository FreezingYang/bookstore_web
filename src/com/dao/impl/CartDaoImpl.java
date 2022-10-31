package com.dao.impl;

import com.dao.BookDao;
import com.dao.CartDao;
import com.entity.Book;
import com.entity.Cart;
import com.entity.CartItem;
import com.entity.User;
import com.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public List<CartItem> getCartItemList(User user){
        String sql = "select * from t_cart_item where user_id = ?";
        List<CartItem> cartItemList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartItem.class), user.getUserId());
        for (CartItem item : cartItemList) {
            Book book = bookDao.findAllBookById(item.getBookId());
            item.setBook(book);
            BigDecimal price = BigDecimal.valueOf(book.getPrice());
            BigDecimal buyCount = BigDecimal.valueOf(item.getBuyCount());
            BigDecimal multiply = price.multiply(buyCount);
            item.setSum(multiply.doubleValue());
        }
        Cart cart = new Cart();
        cart.setCartItemList(cartItemList);
        user.setCart(cart);
        return cartItemList;
    }

    @Override
    public void addCartItem(CartItem cart) {
        String sql = "insert into t_cart_item values(0, ?, ?, ?)";
        jdbcTemplate.update(sql, cart.getBookId(), cart.getBuyCount(), cart.getUserId());
    }

    @Override
    public void updateCartItem(Integer buyCount, Integer id) {
        String sql = "update t_cart_item set buy_count = ? where id = ? ";
        jdbcTemplate.update(sql, buyCount, id);
    }

    @Override
    public void deleteCartItem(Integer bookId, Integer userId) {
        String sql = "delete from t_cart_item where book_id = ? and user_id = ?";
        jdbcTemplate.update(sql, bookId, userId);
    }

    @Override
    public void emptyCartItem(User user) {
        String sql = "delete from t_cart_item where user_id = ?";
        jdbcTemplate.update(sql, user.getUserId());
    }
}
