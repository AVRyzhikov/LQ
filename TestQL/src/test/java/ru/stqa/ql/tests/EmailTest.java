package ru.stqa.ql.tests;

import org.testng.annotations.Test;

public class EmailTest extends TestBase {
  @Test
  public void SendingLetterTest() throws InterruptedException {
    app.SendingLetter(user, letter);
  }
}
