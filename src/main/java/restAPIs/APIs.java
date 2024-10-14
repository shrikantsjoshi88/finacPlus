package restAPIs;

import CommonMethodsAndUtilities.CommonMethodsAndUtilities;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class APIs extends CommonMethodsAndUtilities {

    public static Response fetchUsersList(String contentType, String value,String endPoint) {
        Response res = given(requestSpecification(contentType))
                .queryParam("page","value")
                .when()
                .get(endPoint);
        return res;
    }
}
