package ApplicationPages;

import DriverManager.DriverManager;
import CommonMethodsAndUtilities.CommonMethodsAndUtilities;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonMethodsAndUtilities {

    public static By USERNAME = By.xpath("//*[@id='user-name']");
    public static By PASSWORD = By.xpath("//*[@id='password']");
    public static By LOGIN = By.xpath("//*[@id='login-button']");
    public static By ERRORMSG = By.xpath("//*[@id='login_button_container']/div/form/div[3]");


    @Step("Launched browser access URL")
    public WebDriver launchURL() {
        driver=DriverManager.getInstance(environmentConfig.browser()).getDriver();
        driver.manage().window().maximize();
        driver.get(environmentConfig.baseURL());
        return driver;
    }

    @Step("User Login")
    public WebDriver login(String userName) {
        enterText(USERNAME, userName);
        enterText(PASSWORD, environmentConfig.passKey());
        clickElement(LOGIN);
        try {
            acceptAlert();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }







}
