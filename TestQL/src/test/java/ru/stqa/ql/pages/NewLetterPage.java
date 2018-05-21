package ru.stqa.ql.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewLetterPage extends PageObject {

  @FindBy(xpath = "//textarea[@data-original-name='To']")
  private WebElement to;

  @FindBy(name = "Subject")
  private WebElement subject;

  @FindBy(xpath = "//iframe")
  private WebElement iframe;

  @FindBy(xpath = "//span[.='Отправить']")
  private WebElement submitButton;


  public NewLetterPage(WebDriver driver) {
    super(driver);
  }

  public boolean isInitialized() {
    return to.isDisplayed() && subject.isDisplayed();
  }

  public void enterTo(String inputTo) {
    type(to, inputTo);

  }

  public void enterSubject(String inputSubject) {
    type(subject, inputSubject);

  }

  public void enterBody(String inputBody) {

    driver.switchTo().frame(iframe);
    type(driver.findElement(By.xpath("//body")), inputBody);
    driver.switchTo().defaultContent();

  }

  public LettersPage send() {
    submitButton.click();
    return new LettersPage(driver);

  }
}