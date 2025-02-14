package org.fidoTest.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fidoTest.api.payload.UserCredentials;
import org.testng.annotations.BeforeClass;

public class AbstractTest {
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";

    public Logger LOGGER; // Logging instance
    public UserCredentials userPayload;

    @BeforeClass
    public void setup() {
        initializeUserPayload();
        LOGGER = LogManager.getLogger(this.getClass());
    }

    private void initializeUserPayload() {
        userPayload = new UserCredentials();
        userPayload.setUsername(DEFAULT_USERNAME);
        userPayload.setPassword(DEFAULT_PASSWORD);
    }
}