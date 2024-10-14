package Base;

import ApplicationPages.CartPage;
import ApplicationPages.LoginPage;
import ApplicationPages.ProductsPage;
import Listeners.EnvironmentConfig;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Base {

    public static LoginPage loginPage;
    public static ProductsPage productsPage;
    public static CartPage cartPage;
    public static EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class);

    public static List<WebElement> list;
    public static List<String> text;
    public static String[] productText;

    public static WebDriverWait wait;

    public static List<String> productList;
    public static List<String> sortedProductList;
    public static List<String> priceList;
    public static List<String> sortedPriceList;

    public static String productName;
    public static String productPrice;
    public static String cartItemName;

    public static String errorMessage;

    public static long startTime;
    public static long endTime;

    public static long responseTime;
    public static int randomValue;

    public static Response response;
    public static Set<Map.Entry<String,String>> finalSet;

    public static String validEndpoint = "/api/users";
    public static String invalidEndpoint = "/api/us#@$##$fsc";

}


