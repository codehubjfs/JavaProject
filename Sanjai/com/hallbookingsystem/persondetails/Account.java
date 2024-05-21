package com.hallbookingsystem.persondetails;
public class Account {
    private String username;
    private String password;
    private AccountStatus status;
    private AccountType type;

    public Account(String username, String password, AccountStatus status, AccountType type) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.type = type;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
