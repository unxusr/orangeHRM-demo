package test.java.stepDefinitions;

import io.cucumber.java.en.*;
import pageObjects.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminSteps {

    private WebDriver driver;
    public LoginPage loginPage;
    public AdminPage adminPage;
    Faker faker = new Faker();
    String firstName;
    String lastName;

    @Before
    public void setUp() {
        System.out.println("Setting up WebDriver");
        driver = new ChromeDriver();
        adminPage = new AdminPage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down WebDriver");
        if (driver != null) {
            try {
                // Add a short delay before quitting the driver
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

    @Given("the user is on the OrangeHRM login page")
    public void openLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @When("the user enters {string} as username")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("the user enters {string} as password")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("the user clicks on the login button")
    public void clickLoginButton() {
        loginPage.clickLogin();
    }


    @When("clicks on the PIM tab")
    public void clicks_on_the_pim_tab() {
        // Write code here that turns the phrase above into concrete actions
        adminPage.clickPimTab();
    }
    @When("user click on the Add Employee tab")
    public void user_click_on_the_add_employee_tab() {
        // Write code here that turns the phrase above into concrete actions
        adminPage.clickAddEmployeeTab();
    }
    @When("the user enters employee first name {string}")
    public void the_user_enters_employee_first_name(String firstName) {
        // Write code here that turns the phrase above into concrete actions
        //firstName = faker.name().firstName();
        adminPage.fillInFirstName(firstName);
    }
    @When("the user enters employee last name {string}")
    public void the_user_enters_employee_last_name(String lastName) {
        // Write code here that turns the phrase above into concrete actions
        lastName = faker.name().lastName();
        adminPage.fillInLastName(lastName);
    }
    @When("the user enters employee id {string}")
    public void the_user_enters_employee_id(String employeeId) {
        // Write code here that turns the phrase above into concrete actions
        employeeId = faker.number().digits(5);
        adminPage.fillInEmployeeId(employeeId);
    }
    @When("user saves the employee data")
    public void user_saves_the_employee_data() {
        // Write code here that turns the phrase above into concrete actions
        adminPage.saveEmployeeData();
        adminPage.verifyEmployeeProfilePage();
    }
    

    @And("the user clicks on the Admin tab")
    public void clickAdminTab() {
        adminPage.clickAdminTab();
    }

    @Then("the user should see the number of records found")
    public void getNumberOfRecords() {
        adminPage.getNumberOfRecords();
        String recordsText = adminPage.getNumberOfRecordsText();
        assertTrue("Number of records not found", recordsText.contains("Records Found"));
    }

    @When("the user clicks on the Add button")
    public void clickAddButton() {
        adminPage.clickAddButton();
        assertTrue("Add user form is displayed", adminPage.verifyAddUserFormIsDisplayed());
    }

    @And("the user fills the required data")
    public void fillRequiredData() {
        adminPage.fillUserData();
    }

    @And("the user clicks on the Save button")
    public void clickSaveButton() {
        adminPage.clickSaveButton();
    }

    @Then("the number of records should increase by one")
    public void verifyRecordIncrease() {
        assertTrue("Records has increased", adminPage.verifyRecordCountIncreased());
    }

    @When("the user searches for the new user by username")
    public void searchForUser() {
        adminPage.searchForUser();
    }

    @Then("the user should see the new user in the search results")
    public void verifyUserFound() {
        assertTrue("New user found in search results", adminPage.verifyUserFound());
    }

    @When("the user deletes the new user")
    public void deleteUser() {
        adminPage.deleteUser();
    }

    @Then("the number of records should decrease by {int}")
    public void verifyRecordDecrease(int count) {
        assertTrue("Records has decreased", adminPage.verifyRecordCountDecreased(count));
    }

    
}
    