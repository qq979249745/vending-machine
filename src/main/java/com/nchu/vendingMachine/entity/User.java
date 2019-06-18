package com.nchu.vendingMachine.entity;

import javax.validation.constraints.Pattern;

public class User {
    private Integer id;
    @Pattern(regexp = ".{5,16}",message = "账号长度为5到16个字符")
    private String account;
    @Pattern(regexp = ".{5,16}",message = "密码长度为5到16个字符")
    private String password;

    private String email;

    private String address;

    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
}