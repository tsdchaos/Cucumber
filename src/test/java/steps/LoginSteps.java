package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

public class LoginSteps extends CommonMethods{

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndLaunchApplication();
    }

    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
        WebElement username= driver.findElement(By.id("txtUsername"));
        username.sendKeys("Admin");
        WebElement password = driver.findElement(By.id("txtPassword"));
        password.sendKeys("Hum@nhrm123");

    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        WebElement loginButton =  driver.findElement(By.id("btnLogin"));
        loginButton.click();
    }

    @Then("admin user is successfully logged in")
    public void admin_user_is_successfully_logged_in() {
        System.out.println("Test Passed");
    }
    @Given("user is xyz")
    public void user_is_xyz() {
        System.out.println("hello");
    }
    @Then("user closes the browser")
    public void user_closes_the_browser() {
        closeBrowser();
    }
    @When("user enters different {string} and {string} and verify the {string} for all the combinations")
    public void user_enters_different_and_and_verify_the_for_all_the_combinations(String userName, String passWord, String error) {
        WebElement username= driver.findElement(By.id("txtUsername"));
        username.sendKeys(userName);
        WebElement password = driver.findElement(By.id("txtPassword"));
        password.sendKeys(passWord);
        WebElement loginButton =  driver.findElement(By.id("btnLogin"));
        loginButton.click();
        WebElement errorMessage = driver.findElement(By.id("spanMessage"));
        String errorActual = errorMessage.getText();
        Assert.assertEquals("values do not match", error, errorActual);
    }
    @When("user enters different {string} and {string}")
    public void user_enters_different_and(String userNameValue, String passwordValue) {
        WebElement username= driver.findElement(By.id("txtUsername"));
        username.sendKeys(userNameValue);
        WebElement password = driver.findElement(By.id("txtPassword"));
        password.sendKeys(passwordValue);

    }
    @Then("{string} user is successfully logged in")
    public void user_is_successfully_logged_in(String admin) {
        WebElement dashboardMessage = driver.findElement(By.id("welcome"));
        Assert.assertTrue(dashboardMessage.isDisplayed());
    }
}
