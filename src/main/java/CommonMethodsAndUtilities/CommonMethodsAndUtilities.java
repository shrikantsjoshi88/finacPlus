package CommonMethodsAndUtilities;

import Base.Base;
import DriverManager.*;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.io.*;
import java.time.Duration;
import java.util.*;

import static io.restassured.RestAssured.with;

public class CommonMethodsAndUtilities extends Base {

    public static By BURGERICON = By.xpath("//*[@id='react-burger-menu-btn']");
    public static By LOGOUTLINK = By.xpath("//*[@id='logout_sidebar_link']");

    public static WebDriver driver;

    /**
     * This method is used to enter text
     */
    public WebDriver enterText(By element, String text) {
        driver.findElement(element).sendKeys(text);
        return driver;
    }

    /**
     * This method is used for waiting time
     */
    public static WebDriver waitTime(int value) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(value));
        return driver;
    }

    /**
     * This method is used to perform click event
     */
    public static WebDriver clickElement(By element) {
        driver.findElement(element).click();
        return driver;
    }

    /**
     * This method is used to get text
     */
    public static String getText(By element) {
        errorMessage = driver.findElement(element).getText();
        return errorMessage;
    }

    /**
     * This method is used to accept alert
     */
    public WebDriver acceptAlert() {
        driver.switchTo().alert().accept();
        return driver;
    }

    /**
     * This method is used to find element
     */
    public WebElement findElement(By element) {
        return driver.findElement(element);
    }

    /**
     * This method is used to verify if the element is displayed
     */
    public WebDriver isDisplayed(By element) {
        findElement(element).isDisplayed();
        return driver;
    }

    /**
     * This method is used to select element from dropdown
     */
    public WebDriver selectByVisibleText(By dropdownElement, String visibleText) {
        Select select = new Select(findElement(dropdownElement));
        select.selectByVisibleText(visibleText);
        return driver;
    }

    /**
     * This method is used to perform click event using java script
     */
    public void clickElementByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * This method is used to find list of elements
     */
    public List<WebElement> findElements(By element) {
        return driver.findElements(element);
    }

    /**
     * This method is used to get product details
     */
    public WebDriver getProductDetails(By elementBy) {
        list = findElements(elementBy);
        text = new ArrayList<>();
        productList = new ArrayList<>();
        priceList = new ArrayList<>();
        for (WebElement webElement : list) {
            text.add(webElement.getText());
            String[] pName = webElement.getText().split("\n");
            if (pName.length > 0) {
                productList.add(pName[0]);
                priceList.add(pName[2]);
            }
        }
        productText = text.get(0).split("\n");
        productName = productText[0];
        productPrice = productText[2];
        writeToFile(productName, productPrice);
        Reporter.log("The first product name is: " + productName, true);
        Reporter.log("The first product price is: " + productPrice, true);
        return driver;
    }

    /**
     * This method is used to write data to file
     */
    public void writeToFile(String Name, String Price) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/productDetails.txt", true))) {
            writer.write("Product: " + Name + ", Price: " + Price);
            writer.newLine();
            Reporter.log("The details are logged in a file", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to get cart details
     */
    public WebDriver getCartDetails(By elementBy) {
        List<WebElement> list = findElements(elementBy);
        List<String> text = new ArrayList<>();
        for (WebElement webElement : list) {
            text.add(webElement.getText());
        }
        String[] cartItemText = text.get(0).split("\n");
        cartItemName = cartItemText[1];
        Reporter.log("Cart details are: " + cartItemName, true);
        return driver;
    }

    /**
     * This method is used to take screenshot
     */

    public WebDriver takeScreenShot() {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("path/to/save/screenshot.png");
            FileHandler.copy(screenshot, destinationFile);
            Reporter.log("Screenshot captured");
        } catch (IOException e) {
            Reporter.log("An error occurred while saving the screenshot: " + e.getMessage());
        }
        return driver;
    }

    /**
     * This method is used to delete reports history
     */
    public static void deleteReportsHistory() {
        File resultDirectory = new File("allure-results");
        try {
            FileUtils.cleanDirectory(resultDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to open up the test report
     */
    public static void generateReports() {
        String allureCommand = "cmd /c start cmd.exe /K allure serve allure-results";
        try {
            Runtime.getRuntime().exec(allureCommand);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * This method is used to logout
     */

    public static void Logout() {
        waitTime(10);
        clickElement(BURGERICON);
        waitTime(10);
        clickElement(LOGOUTLINK);
    }

    /**
     * This method is used for request specification
     */
    public static RequestSpecification requestSpecification(String contentType) {
        RequestSpecification rspec = with()
                .baseUri(environmentConfig.baseURI())
                .header("Content-Type", contentType);
        return rspec;

    }

    /**
     * This method is used for response specification
     */
    public static ResponseSpecification responseSpecification() {
        ResponseSpecification resspec = with()
                .expect()
                .statusCode(200);
        return resspec;

    }

    /**
     * This method is used for setting query params
     */

    public static HashMap<String, String> setQueryParams(String key, String value) {
        HashMap map = new HashMap<>();
        map.put(key, value);
        for (Object entry : map.entrySet()) {
            finalSet = new HashSet<>();
            finalSet.add((Map.Entry<String, String>) entry);
        }
        return map;
    }

    /**
     * This method is used for report attachments
     */
    public static void reportAttachments(String endpoint, String request, String response, int statusCode) {
        if (statusCode == 200) {
            removeParameters();
            Allure.addAttachment("RequestReponse", "application/json",
                    "\n\nEndpoint: " + endpoint +
                            "\n\nQuery Params: " + finalSet +
                            "\n\nRequest: " + "\n" + request +
                            "\n\nStatus Code: " + "\n" + statusCode +
                            "\n\nResponse: " + "\n" + response);


        } else {
            removeParameters();
            Allure.addAttachment("RequestReponse", "application/json",
                    "\n\nEndpoint: " + endpoint +
                            "\n\nQuery Params: " + finalSet +
                            "\n\nRequest: " + "\n" + request +
                            "\n\nStatus Code: " + "\n" + statusCode +
                            "\n\nResponse: " + "\n" + response);


        }
    }


    /**
     * This method is used for attaching error message
     */

    public static void reportAttachments(String errorMessage) {
        Allure.addAttachment("Exception/Error Message: ", errorMessage);
    }

    /**
     * This method is used for removing parameters
     */

    private static void removeParameters() {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.updateTestCase(testResult -> testResult.setParameters(new LinkedList<>()));
    }
}



