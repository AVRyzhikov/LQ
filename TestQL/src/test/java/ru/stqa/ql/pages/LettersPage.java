package ru.stqa.ql.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LettersPage extends PageObject {


  @FindBy(xpath = "//span[.='Написать письмо']")
  private WebElement submitButton;

  @FindBy(xpath = "//a[@id='PH_logoutLink']")
  private WebElement submitLogout;

  public LettersPage(WebDriver driver) {
    super(driver);
  }

  public boolean isInitialized() {
    return submitButton.isDisplayed();
  }


  public NewLetterPage submit() {
    submitButton.click();
    return new NewLetterPage(driver);

  }

  public void logout() {
    submitLogout.click();
  }
}
