/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginTest;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author ruinan
 */
public class LoginTest {
    
    private WebDriver driver;
    private WebDriverWait wait;
    /*
        User Story
    
        As a user
        I want to login
        So that I can access my account
    
    */
    @Before
    public void setup() {
        
        driver = new ChromeDriver();
        driver.get("http://store.demoqa.com/products-page/your-account/");
    }
    
    @After
    public void teardown() {
        driver.quit();
    }
    /*
    Scenario1: Login with correct username and password
    Given: correct username and password
    When: I try login with this credentials
    Then: I should login successfully
    */
    @Test
    public void correctUsernameAndPassword(){
       
        driver.findElement(By.id("log")).sendKeys("n");//input username
        driver.findElement(By.id("pwd")).sendKeys("01bQ!i5@%BKC@dP!");//input password
        driver.findElement(By.id("login")).click();//click
       
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//set waiting time
        assertEquals(driver.findElement(By.xpath("//*[@id=\"account_logout\"]/a")).getText(),"(Logout)");   
    }
    
    /*
    Scenario2: Login with incorrect username and correct password
    Given: incorrect username "rui" and correct password
    When: I try to login with this credentials
    Then: I should receive warning message "ERROR"
    */
    
    @Test
    public void incorrectUsernameWithCorrectPassword(){
        driver.findElement(By.id("log")).sendKeys("rui");//input username
        driver.findElement(By.id("pwd")).sendKeys("01bQ!i5@%BKC@dP!");//input password
        driver.findElement(By.id("login")).click();//click
       
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//set waiting time
        assertEquals(driver.findElement(By.xpath("//*[@id=\"ajax_loginform\"]/p[1]/strong")).getText(),"ERROR"); 
    }
    
    /*
    Scenario3: Login with correct username and incorrect password
    Given: correct username "r" and incorrect password "12345"
    When: I try to login with this credentials
    Then: I should receive warning message "ERROR"
    */
    @Test
    public void correctUsernameWithIncorrectPassword(){
        driver.findElement(By.id("log")).sendKeys("n");//input username
        driver.findElement(By.id("pwd")).sendKeys("12345");//input password
        driver.findElement(By.id("login")).click();//click
       
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//set waiting time
        assertEquals(driver.findElement(By.xpath("//*[@id=\"ajax_loginform\"]/p[1]/strong")).getText(),"ERROR"); 
    }
    
    /*
    Scenario4: Login with empty input
    Given: correct username "r" and incorrect password "12345"
    When: I try to login with this credentials
    Then: I should receive warning message "ERROR"
    */
    @Test
    public void emptyUsernameAndPassword(){
        driver.findElement(By.id("login")).click();//click
       
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//set waiting time
        assertEquals(driver.findElement(By.xpath("//*[@id=\"ajax_loginform\"]/p[1]")).getText(),"Please enter your username and password."); 
    }
    
    
}
