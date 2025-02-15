package org.fidoTest.base;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fidoTest.api.payload.UserCredentials;
import org.fidoTest.api.payload.VideoGamesDetails;
import org.testng.annotations.BeforeClass;

import java.util.Random;

public class AbstractTest {
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";
    // Shared token field
    public static String token;

       Faker faker;
    public Logger LOGGER; // Logging instance
    public UserCredentials userPayload;
    public VideoGamesDetails videoGamesPayload;

    @BeforeClass
    public void setup() {
        initializeUserPayload();
        initializeVideoGamePayload();
        LOGGER = LogManager.getLogger(this.getClass());
    }

    private void initializeUserPayload() {
        userPayload = new UserCredentials();
        userPayload.setUsername(DEFAULT_USERNAME);
        userPayload.setPassword(DEFAULT_PASSWORD);
    }

    private void initializeVideoGamePayload() {
        videoGamesPayload = new VideoGamesDetails();
        faker = new Faker();
        videoGamesPayload.setCategory(faker.name().title());
        videoGamesPayload.setName(faker.gameOfThrones().character());
        videoGamesPayload.setRating(faker.gameOfThrones().city());
        videoGamesPayload.setReleaseDate(faker.date().birthday().toString());
        videoGamesPayload.setReviewScore(faker.number().numberBetween(1, 5));
        videoGamesPayload.setId(new Random().nextInt(10) + 1);
        videoGamesPayload.setToken(token);


    }

}