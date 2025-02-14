package org.fidoTest.api.endpoints;

public class Routes {
    private static final String BASE_URL = "https://www.videogamedb.uk:443";
    private static final String API_PATH = "/api";
    private static final String VIDEO_GAME_PATH = API_PATH + "/videogame";
    private static final String AUTHENTICATE_PATH = API_PATH + "/authenticate";

    // API Authentication Controller
    public static final String AUTHENTICATE_URL = BASE_URL + AUTHENTICATE_PATH;

    // API Video Games Controller
    public static final String GET_ALL_GAMES_URL = BASE_URL + VIDEO_GAME_PATH;
    public static final String CREATE_GAME_URL = BASE_URL + VIDEO_GAME_PATH;
    public static final String GET_GAME_BY_ID_URL = BASE_URL + VIDEO_GAME_PATH + "/{id}";
    public static final String UPDATE_GAME_URL = BASE_URL + VIDEO_GAME_PATH + "/{id}";
    public static final String DELETE_GAME_URL = BASE_URL + VIDEO_GAME_PATH + "/{id}";
}