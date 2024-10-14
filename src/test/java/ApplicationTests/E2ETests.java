package ApplicationTests;

import ApplicationPages.CartPage;
import ApplicationPages.LoginPage;
import ApplicationPages.ProductsPage;
import CommonMethodsAndUtilities.CommonMethodsAndUtilities;
import io.qameta.allure.Description;
import org.testng.Reporter;
import org.testng.annotations.*;

import static ApplicationPages.LoginPage.*;
import static DriverManager.DriverManager.*;
import static org.assertj.core.api.Assertions.*;

public class E2ETests extends CommonMethodsAndUtilities {
    @BeforeTest(description = "Prerequisite test for creating prerequisite activities")
    @Description("Prerequisite test for creating prerequisite activities")
    public void ObjectCreation() {
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        deleteReportsHistory();
    }

    @Test(description = "assertLockedOutUser")
    public void assertLockedOutUser() {
        loginPage.launchURL();
        loginPage.login(environmentConfig.lockedOutUser());
        assertThat(getText(ERRORMSG)).isEqualTo("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(description = "assertProblemUser")
    public void assertProblemUser() {
        loginPage.launchURL();
        loginPage.login(environmentConfig.problemUser());
        productsPage.productDetails();
        try {
            productsPage.sort("Name (Z to A)");
            productsPage.productDetails();
            productsPage.sort("Price (high to low)");
            productsPage.productDetails();
            if (sortedProductList.equals(productList) && sortedPriceList.equals(priceList)) {
                Reporter.log("The sorting is not working as expected!!", true);
            }
        } catch (Exception e) {
            Reporter.log("The sort functionality is not working. There is a problem with the user credentials" + e.getMessage(), true);
        }
        Logout();
    }

    @Test(description = "assertPerformanceUser", invocationCount = 5)
    public void assertPerformanceUser() {
        try {
            loginPage.launchURL();
            loginPage.login(environmentConfig.performanceGlitchUser());
            startTime = System.currentTimeMillis();
            productsPage.productDetails();
            endTime = System.currentTimeMillis();
            responseTime = endTime - startTime;
            if (responseTime > 100) {
                Reporter.log("Warning: Response time (" + responseTime + " ms) exceeded the acceptable limit (" + 100 + " ms)", true);
            } else {
                Reporter.log("Response time is acceptable (" + responseTime + " ms)", true);
            }
            Logout();
        } catch (Exception e) {
            Reporter.log("Exception occurred: " + e.getMessage(), true);
        }
    }


    @Test(description = "assertErrorUser", invocationCount = 10)
    public void assertErrorUser() {
        try {
            loginPage.launchURL();
            loginPage.login(environmentConfig.errorUser());
            productsPage.productDetails();
        } catch (Exception e) {
            Reporter.log("ERROR!!!" + e.getMessage(), true);
        }
        Logout();
    }

    @Test(description = "assertVisualUser")
    public void assertVisualUser() {
        loginPage.launchURL();
        loginPage.login(environmentConfig.visualUser());
        productsPage.productDetails();
        Logout();
    }

    @Test(description = "addAndAssertCartItemWithValidCredentials")
    public void addAndAssertCartItemWithValidCredentialsAndStoreInTextFile() {
        loginPage.launchURL();
        loginPage.login(environmentConfig.userName());
        productsPage.productDetails();
        productsPage.clickAddToCart(0);
        cartPage.CartDetails();
        assertThat(cartItemName).isEqualTo(productName);
        Logout();
    }

    @AfterClass(description = "Tear down test to generate reports")
    @Description("Tear down test to generate report")
    public void tearDown() {
        quitBrowser();
        generateReports();
    }
}
