package br.unicamp.ic.mo409.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginInvalidoTest {
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
  public void testLoginInvalido() throws Exception {
    driver.get(baseUrl + "/#!/app/login");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("35");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("userr");
    driver.findElement(By.xpath("//button")).click();
    assertEquals("Bad credentials", closeAlertAndGetItsText());    
  }
  
  @Test
  public void testLoginAluno() throws Exception {
    driver.get(baseUrl + "/#!/app/login");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("17");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("user");
    driver.findElement(By.xpath("//button")).click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.getPageSource().contains("Professor");
    driver.findElement(By.xpath("/html/body/ion-nav-view/ion-side-menus[2]/ion-side-menu-content/ion-nav-bar/div[2]/ion-header-bar/div[2]")).getText().contains("Professor");
    driver.findElement(By.xpath("//ion-content/div/button")).click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(By.xpath("//span/button")).click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    //driver.findElement(By.linkText("Home")).click();
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
