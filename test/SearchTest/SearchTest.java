/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchTest;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author ruinan
 */
public class SearchTest {
     private WebDriver driver;// Because of FireFox driver always made mistakes, so in this test case, I add chromediver into it.
    /*
        User Story
    
        As a user
        I want to search product by its name
        So that I can find the product quickly
    
    */
    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://store.demoqa.com");

    }
    
    @After
    public void teardown() {
        driver.quit();
        
    }
    /*
    Scenario1: Search with specific name 
    Given: An specific name "Sennheiser RS 120" which is a name of item in the store
    When: Input the specific name into search bar
    And: Click search button
    Then: The corresponding item will be listed
    */
    
    @Test
    public void searchWithSpecificName(){
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"main-nav\"]/form/fieldset/input[1]"));
        searchBar.sendKeys("Sennheiser RS 120");
        searchBar.submit();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);// wait for web response
        List<WebElement> results = driver.findElements(By.className("product_grid_item"));
        
        assertEquals(results.size(),1);// the search result only has one item
        assertEquals(results.get(0).findElement(By.className("prodtitle")).getText(),"Sennheiser RS 120");//The name of item which be find out should be identical with the searching word
                
    }
    
     /*
    Scenario2: Search with ambiguous name 
    Given: An ambiguous name "Apple" which is the part of name of items in store
    When: Input the ambiguous name into search bar
    And: Click search button
    Then: The related items will be listed
    */
    
    @Test
    public void searchWithAmbiguousName(){
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"main-nav\"]/form/fieldset/input[1]"));
        searchBar.sendKeys("Apple");
        searchBar.submit();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);// wait for web response
        List<WebElement> results = driver.findElements(By.className("product_grid_item"));
        
        assertEquals(results.size(),9);// The search reasult should be 9
        results.stream().map((e) -> e.findElement(By.className("prodtitle")).getText()).map((name) -> name.substring(0, 5)).map((Key) -> {
             return Key;
         }).forEach((Key) -> {
             assertEquals(Key,"Apple");// The name of items which be find out should has searching key word.
         });
    }
    
}
