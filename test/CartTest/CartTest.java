/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CartTest;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author ruinan
 */
public class CartTest {
    
  
    private WebDriver driver;// Because of FireFox driver always made mistakes, so in this test case, I add chromediver into it.
    /*
        User Story
    
        As a user
        I want to decide which item I want to buy
        So that I can manage my cart
    
    */
    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://store.demoqa.com/products-page/product-category/");

    }
    
    @After
    public void teardown() {
        driver.quit();
        
    }
    
    /*
    Scenario1: Add items into cart
    Given: An item which I want to add to cart
    When: I click "add to cart" button
    Then: The item will successfully added into my cart
    */
    @Test
    public void addToCart(){
        
        //There are two kinds of Categories to show products. Therefore, I should consider two situations.
        String name;
        WebElement addtocart = driver.findElement(By.xpath("//*[@id=\"default_products_page_container\"]/div[3]/div[2]/form/div[2]/div[1]/span/input"));
        if(addtocart == null){
            addtocart = driver.findElement(By.xpath("//*[@id=\"grid_view_products_page_container\"]/div[3]/div[1]/div[3]/form/div/div[1]/span/input"));
            name = driver.findElement(By.xpath("//*[@id=\"grid_view_products_page_container\"]/div[3]/div[1]/div[2]/h2/a")).getText();
        }
        else{
            name = driver.findElement(By.xpath("//*[@id=\"default_products_page_container\"]/div[3]/div[2]/h2/a")).getText();
            System.out.println(name);
        }
        
        addtocart.click();
        
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);// wait for web response
        driver.findElement(By.xpath("//*[@id=\"fancy_notification_content\"]/a[1]")).click();// click "go to checkout" button 
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//wait
        
        String itemName = driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[2]/a")).getAttribute("text");
        System.out.println("xxx"+itemName);
        String quantity = driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[3]/form/input[1]")).getAttribute("value");
        
        assertEquals(quantity,"1");// the quantity should be one;
        assertEquals(itemName,name);// the item should be what I added
    }
     /*
    Scenario2: Change the quantity of item which is in the cart
    Given: An item which already in the cart
    When: I input quantity
    And: I click "update" button
    Then: The total price will change 
    */
    
    @Test
    public void changeQuantity(){
       
       
        this.addItemIntoCart();
        
        WebElement itemQuantity = driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[3]/form/input[1]"));
        itemQuantity.clear();//clear the quantity input bar
        itemQuantity.sendKeys("10");//change the quantity into 10
        driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[3]/form/input[4]")).click();//press "change" button
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);// wait
        assertEquals(driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[5]/span/span")).getText(),"$120.00");// the total price should become to "120"
       
    }
    
    
    /*
    Scenario3: Remove item from cart
    Given: An item which already in the cart
    When: I click "remove" button
    Then: The item will be removed from cart 
    */
    
    @Test
    public void testRemove(){
        this.addItemIntoCart();
        driver.findElement(By.xpath("//*[@id='checkout_page_container']/div[1]/table/tbody/tr[2]/td[6]/form/input[4]")).click();//*[@id="checkout_page_container"]/div[1]/table/tbody/tr[2]/td[6]/form/input[4]
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	WebElement content = driver.findElement(By.xpath("//*[@id=\"post-29\"]/div"));//find empty cart notification
	assertEquals(content.getText(), "Oops, there is nothing in your cart.");// The cart should be empty
    }
    
    /* add an item into for test */
    private void addItemIntoCart(){
        WebElement addtocart = driver.findElement(By.xpath("//*[@id=\"default_products_page_container\"]/div[3]/div[2]/form/div[2]/div[1]/span/input"));
        if(addtocart == null){
            addtocart = driver.findElement(By.xpath("//*[@id=\"grid_view_products_page_container\"]/div[3]/div[1]/div[3]/form/div/div[1]/span/input"));
        }
        
        
        addtocart.click();
        
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);// wait for web response
        driver.findElement(By.xpath("//*[@id=\"fancy_notification_content\"]/a[1]")).click();// click "go to checkout" button 
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);//wait
    }
    
}
