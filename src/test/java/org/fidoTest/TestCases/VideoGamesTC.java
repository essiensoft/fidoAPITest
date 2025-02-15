package org.fidoTest.TestCases;

import io.restassured.response.Response;
import org.fidoTest.api.endpoints.VideoGamesAPIEndpoints;
import org.fidoTest.base.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Integer.parseInt;

/**
 * Author: Felix
 * The VideoGamesTC class contains test cases related to the Video Games API.
 * It verifies API responses such as status code, performance, and data integrity.
 */
public class VideoGamesTC extends AbstractTest {

   private static final int EXPECTED_STATUS_OK = 200;

   private Response createGame(){
      return VideoGamesAPIEndpoints.createVideoGame(videoGamesPayload);
   }
   private Response getListAllGamesResponse() {
      Response response = VideoGamesAPIEndpoints.fetchAllVideoGames();
      response.then().log().all();
      return response;
   }

   private Response getGameByIdResponse(){
      Response response = VideoGamesAPIEndpoints.getVideoGame(videoGamesPayload);
      response.then().log().all();
      return response;

   }
   @Test(priority = 2)
   public void testListAllGames() {
      Response response = getListAllGamesResponse();
      Assert.assertEquals(response.getStatusCode(), EXPECTED_STATUS_OK,
              "Status code mismatch for listing all games");
   }

   @Test(priority = 1)
   public void createGameTest() {
      Response response = createGame();
      response.then().log().all();
      Assert.assertEquals(response.getStatusCode(), EXPECTED_STATUS_OK,
              "Status code mismatch for creating a game");
   }

   @Test(priority = 3)
   public void getGameByIdTest(){
	   Response response=getGameByIdResponse();
       Assert.assertEquals(response.getStatusCode(), EXPECTED_STATUS_OK,
              "Status code mismatch for getting a game by id");
   }
}