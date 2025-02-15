package org.fidoTest.TestCases;

import io.restassured.response.Response;
import org.fidoTest.api.endpoints.AuthenticateEndpoints;
import org.fidoTest.base.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.fidoTest.api.payload.VideoGamesDetails;


/**
 * Author: Felix
 * The AuthenticateTC class contains test cases related to API authentication.
 * It extends the AbstractTest class to utilize common test setup and logging functionality.
 * The class tests various aspects of user authentication, including the response status code,
 * the response schema, and the response performance.
 *
 */
public class AuthenticateTC extends AbstractTest {

    private static final String EXPECTED_CONTENT_TYPE = "application/json";
    private final VideoGamesDetails videoGamesDetails = new VideoGamesDetails();

    private Response getAuthenticationResponse() {
        return AuthenticateEndpoints.authenticateUser(userPayload);
    }

    @Test(priority = 1)
    public void testAuthenticateUser() {
        Response response = getAuthenticationResponse();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);

        token = response.jsonPath().getString("token");
        Assert.assertNotNull(token, "Authentication token is not generated!");
    }

    @Test(priority = 2)
    public void testVerifyAuthenticationResponseSchema() {
        Response response = getAuthenticationResponse();
        Assert.assertEquals(response.contentType(), EXPECTED_CONTENT_TYPE);
        Assert.assertTrue(response.getBody().asString().contains("token"), "Response body does not contain 'token'");
    }

    @Test(priority = 3)
    public void testValidateAuthenticationResponseTime() {
        Response response = getAuthenticationResponse();
        Assert.assertTrue(response.time() < 2000, "Performance failure: Response time exceeds 2 seconds");
    }
}