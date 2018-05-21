package ru.stqa.ql.model;

public class User {
  public String login;
  public String password;

  public User withLogin(String login) {
    this.login = login;
    return this;
  }

  public User withPassword(String password) {
    this.password = password;
    return this;
  }
}
