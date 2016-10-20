#DelivableThree

##Summary

There are three user stories been tested which are "Cart Test", "Login Test" and "Search Test". Because of the incomplete
checking out function, it did not be tested. T

Originally, I planned to use Cucumber for testing. However, all test cases skipped. I spent much time to fix it but failed. For this reason, I have to used JUnit to test. 

For the test, the Login Test focused on testing the Login Process. The login process should not allow incorrect username or passord to login. And the empty input for username and password should be prohibit too. If the webpage has a good design, those problems will not happen.

The Cart Test reflect the test for basic cart operations, like add items, change quantity for item, remove items. 

The Search Test consists by two cases. One is searching with specific name. Another is searching with ambiguous name. The searching result will tell performance of the search function.

For the coding, the xpath brings a lot of troubles. Finally I found the xpath got from chrome will cause incredible errors, like xpath does not exist etc. And the addons for firefox are very hard for use. In order to avoid wasting much time in it, I decide to use ChromeDriver to replace FirefoxDriver. In the project, all test drivers are ChromeDriver and it been added into /usr/include/bin/ folder that could be calling when coding. And in the Mac, System.setProperty() is not necessary.
##User Stories

###Cart Test
####User Story:
	As a user
	I want to decide which item I want to buy
	So that I can manage my cart
	
####Scenario1: Add items into cart
    Given: An item which I want to add to cart
    When: I click "add to cart" button
    Then: The item will successfully added into my cart
####Scenario2: Change the quantity of item which is in the cart
    Given: An item which already in the cart
    When: I input quantity and click "update" button
    Then: The total price will change 
####Scenario3: Remove item from cart
    Given: An item which already in the cart
    When: I click "remove" button
    Then: The item will be removed from cart 

###Login Test
####User Story
	As a user
    I want to login
    So that I can access my account

####Scenario1: Login with correct username and password
    Given: correct username and password
    When: I try login with this credentials
    Then: I should login successfully
####Scenario2: Login with incorrect username and correct password
    Given: incorrect username "rui" and correct password
    When: I try to login with this credentials
    Then: I should receive warning message "ERROR"
####Scenario3: Login with correct username and incorrect password
    Given: correct username "r" and incorrect password "12345"
    When: I try to login with this credentials
    Then: I should receive warning message "ERROR"
####Scenario4: Login with empty input
    Given: Empty input bars
    When: I try to login with this credentials
    Then: I should receive warning message "ERROR"
	
###Search Test
####User Story
	As a user
    I want to search product by its name
    So that I can find the product quickly

####Scenario1: Search with specific name 
    Given: An specific name "Sennheiser RS 120" which is a name of item in the store
    When: Input the specific name into search bar
    And: Click search button
    Then: The corresponding item will be listed
####Scenario2: Search with ambiguous name 
    Given: An ambiguous name "Apple" which is the part of name of items in store
    When: Input the ambiguous name into search bar
    And: Click search button
    Then: The related items will be listed

	
	
	
