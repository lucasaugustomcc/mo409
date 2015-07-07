package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ProfChamada2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8100/#!/app/login";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testProfChamada2() throws Exception {
    driver.get(baseUrl + "/#!/app/login");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("35");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("user");
    driver.findElement(By.xpath("//button")).click();
    driver.findElement(By.xpath("//ion-content/div/button")).click();
    driver.findElement(By.xpath("//input[@value='1']")).click();
    driver.findElement(By.xpath("//div/div/div[2]/button")).click();
    driver.findElement(By.xpath("//div/div/div[2]/button")).click();
    driver.findElement(By.xpath("//div/div[3]/div[2]/button")).click();
    driver.findElement(By.xpath("//div[2]/ion-header-bar/div/span/button")).click();
    driver.findElement(By.linkText("Sair")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
