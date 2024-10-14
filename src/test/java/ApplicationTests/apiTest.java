package ApplicationTests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import restAPIs.APIs;

import static DriverManager.DriverManager.quitBrowser;

public class apiTest extends APIs {

    @BeforeTest(description = "Prerequisite test for creating prerequisite activities")
    @Description("Prerequisite test for creating prerequisite activities")
    public void preRequisites() {
        deleteReportsHistory();
    }

    @Test(description = "getListOfUsers")
    public void getListOfUsers() {
        response = APIs.fetchUsersList("application/json","2",validEndpoint);
        reportAttachments(validEndpoint,"NA",response.asPrettyString(),response.statusCode());
        response.then().spec(responseSpecification());
        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, 200);
    }

    @AfterClass(description = "Tear down test to generate reports")
    @Description("Tear down test to generate report")
    public void tearDown() {
        quitBrowser();
        generateReports();
    }
}
