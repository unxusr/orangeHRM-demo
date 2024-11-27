package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;  
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import com.github.javafaker.Faker;


import java.time.Duration;

public class AdminPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String username;
    private int records;
    private Faker faker = new Faker();
    private String firstName = faker.name().firstName();
    private String password = "Admin123";
    private String numberPart;

    // Locators 
    @FindBy(xpath = "//*[@href='/web/index.php/admin/viewAdminModule']")
    private WebElement adminTab;

    @FindBy(xpath = "//*[@href='/web/index.php/pim/viewPimModule']")
    private WebElement pimTab;

    @FindBy(xpath = "//div[@data-v-5a621acd and @class='orangehrm-horizontal-padding orangehrm-vertical-padding']//span")
    private WebElement numberOfRecords;

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeTab;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//*[@name='lastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//h6[text()='Add User']")
    private WebElement addUserFormTitle;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement employeeIdField;

    @FindBy(xpath = "//button[text()=' Save ']")
    private WebElement saveEmployeeButton;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-name']//h6")
    private WebElement employeeNameFieldInProfilePage;

    @FindBy(xpath ="//*[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement addButton;

    @FindBy(xpath = "//*[contains(text(), '-- Select --')]")
    private WebElement userRole;

    @FindBy(xpath = "//*[contains(text(), 'Admin')]")
    private WebElement adminRole;

    @FindBy(xpath = "//*[@placeholder='Type for hints...']") 
    private WebElement empNameField;

    @FindBy(xpath = "//div[@role='listbox']//div[@role='option'][1]")
    private WebElement firstEmpNameInTheList;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active' and @autocomplete='off']") 
    private WebElement usernameField;

    @FindBy(xpath = "//*[contains(text(), '-- Select --')]") 
    private WebElement statusMenu;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']//div[2]") 
    private WebElement status;

    @FindBy(xpath = "//label[text()='Password']/following::input[1]")
    private WebElement passwordField;

    @FindBy(xpath = "//label[text()='Password']/following::input[2]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement searchByUsernameFieldInput;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row'][1]")
    private WebElement adminResultFirstRow;

    @FindBy(xpath = "//button[i[@data-v-bddebfba and @class='oxd-icon bi-trash']]")
    private WebElement deleteButton;
    
    @FindBy(xpath = "//button[text()=' Yes, Delete ']")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//button[text()=' Search ']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[text()=' Reset ']")
    private WebElement resetButton;

    // Constructor
    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickAdminTab() {
        wait.until(ExpectedConditions.elementToBeClickable(adminTab));
        adminTab.click();
    }

    public boolean verifyAddUserFormIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(saveButton));
        return addUserFormTitle.isDisplayed();
        
    }

    public void clickPimTab() {
        wait.until(ExpectedConditions.elementToBeClickable(pimTab));
        pimTab.click();
    }

    public void clickAddEmployeeTab() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeTab));
        addEmployeeTab.click();
    }

    public String fillInFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.sendKeys(this.firstName);
        return firstName;
    }

    public void fillInLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void fillInEmployeeId(String employeeId) {
        employeeIdField.click();
        employeeIdField.clear();
        employeeIdField.sendKeys(employeeId);
    }

    public void saveEmployeeData() {
        wait.until(ExpectedConditions.elementToBeClickable(saveEmployeeButton));
        saveEmployeeButton.click();
    }

    public void verifyEmployeeProfilePage() {
        wait.until(ExpectedConditions.visibilityOf(employeeNameFieldInProfilePage));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//div[@class='orangehrm-edit-employee-name']//h6"), "")));
        String name = employeeNameFieldInProfilePage.getText();
        System.out.println("Employee name: " + name);
    }

    public String getNumberOfRecordsText() {
        wait.until(ExpectedConditions.visibilityOf(numberOfRecords));
        String recordsInText = numberOfRecords.getText();
        return recordsInText;
    }
    public int getNumberOfRecords() {
        wait.until(ExpectedConditions.visibilityOf(numberOfRecords));
        String recordsText = numberOfRecords.getText();
        System.out.println("Number of records text: " + recordsText);
        // Assuming the text is something like "(1) Record Found"
        String[] parts = recordsText.split(" ");
        numberPart = parts[0].replaceAll("[^0-9]", ""); // Remove non-numeric characters
        System.out.println("Number of records: " + numberPart);
        records = Integer.parseInt(numberPart.trim());
        return records;
    }

    public void clickAddButton() {
        addButton.click();
    }

    public String fillUserData() {
        wait.until(ExpectedConditions.visibilityOf(userRole));
        userRole.click();
        adminRole.click();
        empNameField.sendKeys(this.firstName + " ");
        wait.until(ExpectedConditions.visibilityOf(firstEmpNameInTheList));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firstEmpNameInTheList.click();
        int randomNumber = (int)(Math.random() * 1000 + 1);
        username = "emp_" + randomNumber;
        wait.until(ExpectedConditions.visibilityOf(empNameField));
        usernameField.sendKeys(username);
        statusMenu.click();
        status.click();
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
        return username;
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public boolean verifyRecordCountIncreased() {
        int initialCount = records;
        int newCount = getNumberOfRecords();
        if (newCount > initialCount) {
            System.out.println("The record count has increased.");
            return true;
        } else {
            System.out.println("The record count has not increased.");
            return false;
        }
    }

    public void searchForUser() {
        searchByUsernameFieldInput.sendKeys(username);
        searchButton.click();
    }

    public boolean verifyUserFound() {
        wait.until(ExpectedConditions.visibilityOf(adminResultFirstRow));
        if (adminResultFirstRow.isDisplayed()){
            System.out.println("The user was found in the search results.");
            return true;
        } else {
            System.out.println("The user was not found in the search results.");
            return false;
        }
    }

    public void deleteUser() {
        deleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(confirmDeleteButton));
        confirmDeleteButton.click();
        resetButton.click();
    }

    public boolean verifyRecordCountDecreased(int initialCount) {
        wait.until(ExpectedConditions.visibilityOf(numberOfRecords));
        // Get the new count of records
        int newCount = getNumberOfRecords();
        
        // Compare the new count with the initial count
        if (newCount == records) {
            System.out.println("The record count has decreased.");
            return true;
        } else {
            System.out.println(records);
            System.out.println("The record count has not decreased.");
            return false;
        }
    }
}