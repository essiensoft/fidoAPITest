package org.fidoTest.api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.fidoTest.api.payload.VideoGamesDetails;
import org.fidoTest.base.AbstractTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.fidoTest.api.endpoints.Routes.*;

public class VideoGamesAPIEndpoints  {
    private static final ContentType DEFAULT_CONTENT_TYPE = ContentType.JSON;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    /**
     * Fetches the list of all video games.
     *
     * @return Response containing the list of games.
     */
    public static Response fetchAllVideoGames() {
        return when().get(GET_ALL_GAMES_URL);
    }

    /**
     * Creates a new video game with authorization.
     *
     * @param gameDetails Details of the video game to be created.
     * Authorization token.
     * @return Response of the game creation.
     */
    public static Response createVideoGame(VideoGamesDetails gameDetails) {
        return buildAuthorizedRequest(gameDetails.getToken())
                .body(gameDetails)
                .post(CREATE_GAME_URL)
                .then()
                .log().all() // Logs for debugging during tests.
                .extract()
                .response();
    }

    public static Response getVideoGame(VideoGamesDetails gameDetails) {
        RequestSpecification requestSpec = buildAuthorizedRequest(gameDetails.getToken())
                .pathParam("id", gameDetails.getId());
        return requestSpec.when().get(GET_GAME_BY_ID_URL);
    }


    /**
     * Builds an authorized request with default settings.
     *
     * @param token Authorization token.
     * @return Request specification with headers and settings.
     */
    private static RequestSpecification buildAuthorizedRequest(String token) {
        return given()
                .contentType(DEFAULT_CONTENT_TYPE)
                .accept(DEFAULT_CONTENT_TYPE)
                .header(AUTHORIZATION_HEADER, BEARER_PREFIX + token);
    }
}