package ru.stqa.ql.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ru.stqa.ql.app.Application;
import ru.stqa.ql.model.Letter;
import ru.stqa.ql.model.User;

import java.io.IOException;


public class TestBase {
  public WebDriver driver;
  public User user;
  public Letter letter;
  public Application app;

  @BeforeTest

  public void start() throws IOException {

    app = new Application();
    user = new User().withLogin(app.getProperty("mail.login")).withPassword(app.getProperty("mail.password"));
    letter = new Letter().withAddress(app.getProperty("letter.to"))
            .withSubject(app.getProperty("letter.subject"))
            .withBody(app.getProperty("letter.body"));
  }

  @AfterTest
  public void quit() {
    app.stop();
  }


}
