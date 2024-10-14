package ApplicationPages;

import CommonMethodsAndUtilities.CommonMethodsAndUtilities;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage extends CommonMethodsAndUtilities {

    public static By PRODUCTS = By.xpath("//*[@class='inventory_item_description']");
    public static By ADDTOCART = By.xpath("//*[text()='Add to cart']");
    public static By SORTDROPDOWN = By.xpath("//*[@id='header_container']/div[2]/div/span/select");


    @Step("Navigating to product details page")
    public void productDetails() {

        getProductDetails(PRODUCTS);
    }

    @Step("Click on Add to Cart")
    public void clickAddToCart(int value) {
        findElements(ADDTOCART).get(value).click();
    }

    @Step("Sort product details page")
    public void sort(String input) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickElementByJS(findElement(SORTDROPDOWN));
        selectByVisibleText(SORTDROPDOWN, input);

    }
}
