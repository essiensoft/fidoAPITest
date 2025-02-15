package org.fidoTest.TestCases;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.fidoTest.api.endpoints.AuthenticateEndpoints;
import org.fidoTest.base.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: Felix
 * The AuthenticateTC class contains test cases related to API authentication.
 * It extends the AbstractTest class to utilize common test setup and logging functionality.
 * The class tests various aspects of user authentication, including the response status code,
 * the response schema, and the response performance.
 *
 */
public class AuthenticateTC extends AbstractTest {

    /**
     * Represents the expected content type for API responses.
     * This constant is used to validate that the response content type from the
     * API matches the expected MIME type "application/json".
     */
    private static final String EXPECTED_CONTENT_TYPE = "application/json";

    /**
     * Executes the user authentication request using the provided user credentials.
     * This method utilizes the AuthenticateEndpoints class to send a POST request
     * to the authentication endpoint and retrieve the server's response.
     *
     * @return the server's response object containing the authentication details
     *         such as status code, token, and other response attributes
     */
    private Response getAuthenticationResponse() {
        return AuthenticateEndpoints.authenticateUser(userPayload);
    }

    /**
     * Validates the user authentication process by testing the API response.
     *
     * This method performs the following checks:
     * 1. Ensures the authentication API response status code is 200 (success).
     * 2. Extracts the authentication token from the response and verifies it is not null.
     * 3. Confirms the response content type matches the expected value.
     * 4. Asserts that the response body contains the keyword "token".
     * 5. Verifies the API's response time is within the acceptable limit (less than 2 seconds).
     *
     * Assertions:
     * - The status code of the API response is 200.
     * - The authentication token generated is not null.
     * - The response content type equals the expected `application/json`.
     * - The response body contains a "token".
     * - The response time is less than 2 seconds.
     */
    @Test
    @Description("TC__01 - Test for authentication")
    public void testAuthenticateUser() {
        logger.info("Authenticating user");
        Response response = getAuthenticationResponse();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        token = response.jsonPath().getString("token");
        Assert.assertNotNull(token, "Authentication token is not generated!");
        Assert.assertEquals(response.contentType(), EXPECTED_CONTENT_TYPE);
        Assert.assertTrue(response.getBody().asString().contains("token"), "Response body does not contain 'token'");
        Assert.assertTrue(response.time() < 2000, "Performance failure: Response time exceeds 2 seconds");
    }
}