package ru.stqa.ql.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.ql.model.Letter;
import ru.stqa.ql.model.User;
import ru.stqa.ql.pages.LettersPage;
import ru.stqa.ql.pages.LoginPage;
import ru.stqa.ql.pages.NewLetterPage;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class Application {
  private WebDriver driver;
  private final Properties properties;

  public Application() throws IOException {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    properties = new Properties();
    properties.load(new InputStreamReader((new FileInputStream("src/test/resources/local.properties")), "Cp1251"));
    driver.get(getProperty("web.baseUrl"));

  }


  private void type(WebElement element, String value) {
    element.click();
    element.clear();
    element.sendKeys(value);
  }

  public void SendingLetter(User user, Letter letter) throws InterruptedException {


    LoginPage loginPage = new LoginPage(driver);

    loginPage.enterLogin(user.login);
    loginPage.enterPassword(user.password);

    LettersPage lettersPage = loginPage.submit();
    assertTrue(lettersPage.isInitialized());

    NewLetterPage newLetterPage = lettersPage.submit();
    assertTrue(newLetterPage.isInitialized());

    newLetterPage.enterTo(letter.address);
    newLetterPage.enterSubject(letter.subject);
    newLetterPage.enterBody(letter.body);
    lettersPage = newLetterPage.send();
    lettersPage.logout();

  }

  public void stop() {
    driver.quit();

  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}
