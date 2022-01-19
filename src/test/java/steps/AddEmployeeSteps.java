package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AddEmployeeSteps extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        pimOption.click();
    }

    @When("user clicks on Add employee button")
    public void user_clicks_on_add_employee_button() {
       WebElement addEmployee=driver.findElement(By.id("menu_pim_addEmployee"));
       addEmployee.click();
    }
    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {
        WebElement firstName=driver.findElement(By.id("firstName"));
        firstName.sendKeys("Jimbob");
        WebElement lastName=driver.findElement(By.id("lastName"));
        lastName.sendKeys("TheThird");
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        WebElement saveBtn=driver.findElement(By.id("btnSave"));
        saveBtn.click();
    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Success!");
    }

    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
        WebElement firstNameLoc=driver.findElement(By.id("firstName"));
        firstNameLoc.sendKeys(firstName);
        WebElement middleNameLoc=driver.findElement(By.id("middleName"));
        middleNameLoc.sendKeys(middleName);
        WebElement lastNameLoc=driver.findElement(By.id("lastName"));
        lastNameLoc.sendKeys(lastName);
    }

    @When("user enters direct data {string} {string} and {string}")
    public void user_enters_direct_data_and(String firstName, String middleName, String lastName) {
        WebElement firstNameLoc=driver.findElement(By.id("firstName"));
        firstNameLoc.sendKeys(firstName);
        WebElement middleNameLoc=driver.findElement(By.id("middleName"));
        middleNameLoc.sendKeys(middleName);
        WebElement lastNameLoc=driver.findElement(By.id("lastName"));
        lastNameLoc.sendKeys(lastName);
    }
    @When("user add multiple employees and verify they are added")
    public void user_add_multiple_employees_and_verify_they_are_added(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> employeeNames = dataTable.asMaps();
        for(Map<String, String> emp: employeeNames){
            String firstNameValue = emp.get("firstName");
            String lastNameValue = emp.get("lastName");
            String middleNameValue = emp.get("m iddleName");
            WebElement firstNameLoc=driver.findElement(By.id("firstName"));
            firstNameLoc.sendKeys(firstNameValue);
            WebElement middleNameLoc=driver.findElement(By.id("middleName"));
            middleNameLoc.sendKeys(middleNameValue);
            WebElement lastNameLoc=driver.findElement(By.id("lastName"));
            lastNameLoc.sendKeys(lastNameValue);
            WebElement saveBtn=driver.findElement(By.id("btnSave"));
            saveBtn.click();

            //Assertions in Homework
            WebDriverWait wait=new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("img[alt='Employee Photo']"))));

            WebElement personalDetailsPage = driver.findElement(By.className("personalDetails"));
            Assert.assertTrue(personalDetailsPage.isDisplayed());
            Thread.sleep(5000);



            //to come back again and add employee screen because hooks and background works just one time
            WebElement addEmployee=driver.findElement(By.id("menu_pim_addEmployee"));
            addEmployee.click();

            Thread.sleep(3000);
        }
    }
    @When("user adds multiple employees from excel file using {string} sheet and verify the added employee")
    public void user_adds_multiple_employees_from_excel_file_using_sheet_and_verify_the_added_employee(String sheetName) throws InterruptedException{
        List<Map<String, String>> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH,sheetName);
        Iterator<Map<String, String>> itr = newEmployees.iterator();
        //it checks wheteher we have element or not
        WebElement empList= driver.findElement(By.id("menu_pim_viewEmployeeList"));
        while(itr.hasNext()){
            //it returns the key and value for employees
            Map<String, String> mapNewEmp = itr.next();

            WebElement firstNameLoc=driver.findElement(By.id("firstName"));
            firstNameLoc.sendKeys(mapNewEmp.get("FirstName"));
            WebElement middleNameLoc=driver.findElement(By.id("middleName"));
            middleNameLoc.sendKeys(mapNewEmp.get("MiddleName"));
            WebElement lastNameLoc=driver.findElement(By.id("lastName"));
            lastNameLoc.sendKeys(mapNewEmp.get("LastName"));
            WebElement photo = driver.findElement(By.id("photofile"));
            photo.sendKeys(mapNewEmp.get("Photograph"));

            WebElement checkBox = driver.findElement(By.id("chkLogin"));
            if(!checkBox.isSelected()){
                checkBox.click();
            }
            WebElement userName = driver.findElement(By.id("user_name"));
            WebElement password = driver.findElement(By.id("user_password"));
            WebElement confirmPass = driver.findElement(By.id("re_password"));

            userName.sendKeys(mapNewEmp.get("Username"));
            password.sendKeys(mapNewEmp.get("Password"));
            confirmPass.sendKeys(mapNewEmp.get("Password"));

            WebElement saveBtn=driver.findElement(By.id("btnSave"));
            saveBtn.click();

            WebElement addEmployee=driver.findElement(By.id("menu_pim_addEmployee"));
            addEmployee.click();

            //grab employee id while adding the employee
            WebElement empID = driver.findElement(By.id("personal_txtEmployeeId"));
            String empIDvalue = empID.getAttribute("value");
            System.out.println(empIDvalue);

            Thread.sleep(3000);


            //search it in the employee list

            empList.click();

            WebElement searchByID = driver.findElement(By.id("empsearch_id"));
            searchByID.sendKeys(empIDvalue);
            WebElement searchBtn = driver.findElement(By.id("searchBtn"));
            searchBtn.click();

            //Assert that the searched ID and the actual ID match
            WebElement searchID = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a"));
            String IDonTable = searchID.getText();

            Assert.assertEquals(IDonTable, empIDvalue);


            user_clicks_on_add_employee_button();
        }

    }

}
