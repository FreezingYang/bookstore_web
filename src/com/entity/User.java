package com.entity;

public class User {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String email;
    private Integer administrator;
    private Cart cart;

    public User() {
    }

    public User(Integer userId, String userName, String userPassword, String email) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
    }

    public User(String userName, String userPassword, String email, Integer administrator) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.administrator = administrator;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Integer administrator) {
        this.administrator = administrator;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", administrator=" + administrator +
                ", cart=" + cart +
                '}';
    }
}
