package com.dao.impl;

import com.dao.UserDao;
import com.entity.User;
import com.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public void addBookUser(User user) {
        String sql = "insert into t_user(user_name, user_password, email, administrator) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserName(), user.getUserPassword(), user.getEmail(), user.getAdministrator());
    }

    @Override
    public User findUserByNameAndPassword(String userName, String password) {
        try {
            String sql = "select * from t_user where user_name = ? and user_password = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userName, password);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int findUserByName(String userName) {
        String sql = "select * from t_user where user_name = ?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), userName);
        return userList.size();
    }
}
