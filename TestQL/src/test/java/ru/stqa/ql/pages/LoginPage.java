package ru.stqa.ql.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

  @FindBy(id = "mailbox:login")
  private WebElement login;

  @FindBy(id = "mailbox:password")
  private WebElement password;

  @FindBy(xpath = "//input[@value='Войти']")
  private WebElement submitButton;


  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public boolean isInitialized() {
    return login.isDisplayed() && password.isDisplayed() && submitButton.isDisplayed();
  }

  public void enterLogin(String inputLogin) {
    type(login, inputLogin);

  }

  public void enterPassword(String inputPassword) {
    type(password, inputPassword);

  }

  public LettersPage submit() {
    submitButton.click();
    return new LettersPage(driver);

  }
}
