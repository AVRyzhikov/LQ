package ru.stqa.ql.model;

public class Letter {
  public String address;
  public String subject;
  public String body;

  public Letter withAddress(String address) {
    this.address = address;
    return this;
  }

  public Letter withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public Letter withBody(String body) {
    this.body = body;
    return this;
  }
}
