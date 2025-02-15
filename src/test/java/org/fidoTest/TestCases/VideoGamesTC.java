package org.fidoTest.TestCases;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.fidoTest.api.endpoints.VideoGamesAPIEndpoints;
import org.fidoTest.base.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The VideoGamesTC class contains test cases related to CRUD operations for video games using an API.
 * It extends the AbstractTest class to leverage shared setup, payload initialization, and logging functionality.
 * This class ensures that the operations of creating, retrieving, updating, and deleting video games are tested.
 *
 * The test methods in this class interact with the VideoGamesAPIEndpoints to perform API requests
 * and verify the responses using assertions to ensure expected behavior.
 *
 * Test cases are executed in the following priority:
 * 1. Create a video game.
 * 2. List all video games.
 * 3. Retrieve a video game by ID.
 * 4. Update a video game by ID.
 * 5. Delete a video game by ID.
 */
public class VideoGamesTC extends AbstractTest {

   /**
    * Represents the expected HTTP status code for a successful response.
    * This constant is specifically used for test assertions to verify that the
    * API's response status code matches the expected value when a request
    * completes successfully.
    */
   private static final int EXPECTED_STATUS_OK = 200;

   /**
    * Creates a new video game using the provided payload and sends it to the relevant API endpoint.
    *
    * @return Response object containing details of the API response, including status code and any returned data.
    */
   private Response createGame(){
      return VideoGamesAPIEndpoints.createVideoGame(videoGamesPayload);
   }
   /**
    * Retrieves the response for the API call to list all video games.
    * This method invokes the appropriate API endpoint to fetch the list of all available video games
    * and logs the response details for debugging purposes.
    *
    * @return Response object containing the details of the API response, including the list of games.
    */
   private Response getListAllGamesResponse() {
      Response response = VideoGamesAPIEndpoints.fetchAllVideoGames();
      response.then().log().all();
      return response;
   }

   /**
    * Fetches the details of a specific video game by its ID.
    * Executes a GET request to retrieve the game details and logs the response.
    *
    * @return Response object containing the details of the requested video game.
    */
   private Response getGameByIdResponse(){
      Response response = VideoGamesAPIEndpoints.getVideoGame(videoGamesPayload);
      response.then().log().all();
      return response;
   }
   /**
    * Updates an existing video game using the provided payload.
    * Utilizes the VideoGamesAPIEndpoints.updateVideoGame method to send an HTTP PUT request
    * with the game details included in the payload.
    *
    * @return Response containing the outcome of the update operation,
    *         including the updated game details or error message if applicable.
    */
   private Response updateGame(){
	   return VideoGamesAPIEndpoints.updateVideoGame(videoGamesPayload);
   }

   /**
    * Deletes a video game using the provided game details.
    *
    * @return Response object containing the result of the delete operation.
    */
   private Response deleteGame(){
	   return VideoGamesAPIEndpoints.deleteVideoGame(videoGamesPayload);
   }


   /**
    * Tests the creation of a new video game.
    *
    * This method verifies the behavior of the `createGame` endpoint by performing the following:
    * 1. Invokes the `createGame` method to send a POST request for creating a video game.
    * 2. Logs the entire response data for debugging purposes.
    * 3. Asserts that the response status code matches the expected value of `EXPECTED_STATUS_OK`.
    *
    * Assertion:
    * - The HTTP response status code should match the predefined expected status (EXPECTED_STATUS_OK).
    *
    * Dependencies:
    * - Calls the `createGame()` method to initiate the request.
    * - Relies on the `EXPECTED_STATUS_OK` constant for status code validation.
    *
    * This test assumes a valid payload is provided, and the authentication token is set before execution.
    */
   @Test(priority = 1)
   @Description("Test for creating a game")
   public void createGameTest() {
      logger.info("Creating a game");
      Response response = createGame();
      response.then().log().all();
      Assert.assertEquals(response.getStatusCode(), EXPECTED_STATUS_OK,
              "Status code mismatch for creating a game");
   }

   /**
    * Tests the functionality of listing all video games through the API.
    *
    * This test validates the API endpoint responsible for retrieving all video games
    * by performing the following:
    *
    *   - Sending a request to fetch the list of all games.
    *   - Verifying the HTTP response status code matches the expected success status code.
    *
    *
    * Assertions:
    * - The response status code matches the `EXPECTED_STATUS_OK`.
    *
    * Logs are included for debugging purposes to confirm the response details.
    */
   @Test(priority = 2)
   @Description("Test for listing all games")
   public void testListAllGames() {
      logger.info("Listing all games");
      Response response = getListAllGamesResponse();
      Assert.assertEquals(response.getStatusCode(), EXPECTED_STATUS_OK,
              "Status code mismatch for listing all games");
   }

   /**
    * Tests the functionality of retrieving a video game by its unique identifier.
    *
    * The method performs the following steps:
    * 1. Logs the ID of the game being retrieved for debugging purposes.
    * 2. Sends a request using the {@code getGameByIdResponse} method to fetch the video game details by ID.
    * 3. Validates that the HTTP status code of the response matches the expected value.
    *
    * Assertions:
    * - The status code of the response matches the expected success status code (`EXPECTED_STATUS_OK`).
    *
    * This method is annotated with:
    * - {@code @Test}: Specifies this method as a test case with a priority of 3.
    * - {@code @Description}: Provides a high-level description of the test case.
    */
   @Test(priority = 3)
   @Description("Test for getting a game by id")
   public void getGameByIdTest(){
      logger.info("Getting game with id: " + videoGamesPayload.getId());
	   Response response=getGameByIdResponse();
       Assert.assertEquals(response.getStatusCode(), EXPECTED_STATUS_OK,
              "Status code mismatch for getting a game by id");
   }

   /**
    * Validates the functionality of updating a video game by its unique identifier.
    *
    * This method performs the following:
    * 1. Logs the initiation of the game update operation using the game's ID.
    * 2. Attempts to update the game by calling the `updateGame()` method, which interacts
    *    with the API endpoint responsible for updating video game details.
    * 3. Verifies the HTTP response status code to ensure it matches the expected OK status code.
    *
    * Assertions:
    * - Ensures the API response status code is equal to the predefined expected status code.
    *
    * This test ensures the update functionality for a video game operates as expected and
    * returns the correct response.
    */
   @Test(priority = 4)
   @Description("Test for updating a game by id")
   public void testUpdateGame(){
      logger.info("Updating game with id: " + videoGamesPayload.getId());
      Response response=updateGame();
      Assert.assertEquals(response.getStatusCode(), EXPECTED_STATUS_OK,
              "Status code mismatch for updating a game");
   }

   /**
    * Validates the functionality of deleting a video game by its unique identifier.
    *
    * This test performs the following actions:
    * 1. Logs a message indicating the deletion of a game using its ID.
    * 2. Executes the `deleteGame` method to send a DELETE API request.
    * 3. Asserts that the response status code matches the expected success code
    *    specified by the `EXPECTED_STATUS_OK` constant.
    *
    * Test Assertions:
    * - The response status code must match the pre-defined success status code,
    *   indicating successful deletion.
    *
    * Priority Level: 5
    */
   @Test(priority = 5)
   @Description("Test for deleting a game by id")
   public void testDeleteGame(){
      logger.info("Deleting game with id: " + videoGamesPayload.getId());
      Response response=deleteGame();
      Assert.assertEquals(response.getStatusCode(), EXPECTED_STATUS_OK,
              "Status code mismatch for deleting a game");

   }

}