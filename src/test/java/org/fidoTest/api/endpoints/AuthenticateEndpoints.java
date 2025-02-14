package org.fidoTest.api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.fidoTest.api.payload.UserCredentials;

public class AuthenticateEndpoints {
    private static final ContentType DEFAULT_CONTENT_TYPE = ContentType.JSON;

    public static Response authenticateUser(UserCredentials user) {
        Response authenticationResponse = buildRequestForAuthentication(user)
                .post(Routes.AUTHENTICATE_URL);
        return authenticationResponse;
    }

    private static RequestSpecification buildRequestForAuthentication(UserCredentials user) {
        return given()
                .contentType(DEFAULT_CONTENT_TYPE)
                .accept(DEFAULT_CONTENT_TYPE)
                .body(user);
    }
}
