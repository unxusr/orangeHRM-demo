package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;  
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;

public class AdminPage {

    private WebDriver driver;
    private WebDriverWait wait;
    String username;
    String foundRecords;
    int records;
    String employeeName;
    String firstName;
    String numberPart;

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

    @FindBy(xpath = "//*[@placeholder=\"Type for hints...\"]") 
    private WebElement empNameField;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active' and @autocomplete='off']") 
    private WebElement usernameField;

    @FindBy(xpath = "//*[contains(text(), '-- Select --')]") 
    private WebElement statusMenu;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']//div[2]") 
    private WebElement status;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

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

    public void clickPimTab() {
        wait.until(ExpectedConditions.elementToBeClickable(pimTab));
        pimTab.click();
    }

    public void clickAddEmployeeTab() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeTab));
        addEmployeeTab.click();
    }

    public void fillInFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.sendKeys(firstName);
        System.out.println("First name: " + firstName);
    }

    public void fillInLastName(String lastName) {
        lastNameField.sendKeys(lastName);
        System.out.println("Last name: " + lastName);
    }

    public void fillInEmployeeId(String employeeId) {
        employeeIdField.click();
        employeeIdField.clear();
        employeeIdField.sendKeys(employeeId);
        System.out.println("Employee ID: " + employeeId);
    }

    public void saveEmployeeData() {
        wait.until(ExpectedConditions.elementToBeClickable(saveEmployeeButton));
        saveEmployeeButton.click();
    }

    public void verifyEmployeeProfilePage() {
        wait.until(ExpectedConditions.visibilityOf(employeeNameFieldInProfilePage));
        String name = employeeNameFieldInProfilePage.getText();
        System.out.println("Employee name: " + name);
    }

    public int getNumberOfRecords() {
        wait.until(ExpectedConditions.visibilityOf(numberOfRecords));
        String recordsText = numberOfRecords.getText();
        System.out.println("Number of records text: " + recordsText);
        // Assuming the text is something like "(1) Record Found"
        String[] parts = recordsText.split(" ");
        numberPart = parts[0].replaceAll("[^0-9]", ""); // Remove non-numeric characters
        System.out.println("Number of records: " + numberPart);
        return Integer.parseInt(numberPart.trim());
    }

    public void clickAddButton() {
        addButton.click();
    }

    public String fillUserData() {
        wait.until(ExpectedConditions.visibilityOf(userRole));
        userRole.click();
        adminRole.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(empNameField, Keys.ARROW_DOWN).perform();
        actions.sendKeys(empNameField, Keys.ENTER).perform();
        employeeName = empNameField.getText();
        //int randomNumber = (int)(Math.random() * 1000 + 1);
        //username = "emp_" + randomNumber;
        wait.until(ExpectedConditions.visibilityOf(empNameField));
        usernameField.sendKeys(username);
        statusMenu.click();
        status.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        usernameField.sendKeys(username);
    }

    public void verifyUserFound() {
        
    }

    public void deleteUser() {
        // Delete the user
        // ...
    }

    public void verifyRecordCountDecreased(int initialCount) {
        // Verify that the record count has decreased
        // ...
    }
}