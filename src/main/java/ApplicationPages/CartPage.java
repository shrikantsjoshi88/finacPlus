package ApplicationPages;

import CommonMethodsAndUtilities.CommonMethodsAndUtilities;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Reporter;

import java.security.PublicKey;

public class CartPage extends CommonMethodsAndUtilities {

    public static By CARTICON = By.xpath("//*[@id='shopping_cart_container']");
    public static By CARTLIST = By.xpath("//*[@id='cart_contents_container']/div/div[1]/div[3]");
    public static By MISPLACEDCHECKOUT = By.xpath("//*[@id='checkout']");

    @Step("Navigating to cart details page")
    public void CartDetails() {
        clickElement(CARTICON);
        getCartDetails(CARTLIST);
        if (isDisplayed(MISPLACEDCHECKOUT).equals(true)) {
            Reporter.log("There is a misplaced Checkout button resulting in bad user experience", true);
        } else {
            Reporter.log("All looks good", true);
        }
    }

}
