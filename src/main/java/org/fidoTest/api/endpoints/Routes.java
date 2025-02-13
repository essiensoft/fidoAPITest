package org.fidoTest.api.endpoints;

public class Routes {
    public static String BASE_URL = "https://www.videogamedb.uk/";

    //    Api Authentication Controller
    public static String POST_URL = BASE_URL+"/api/authenticate";
    //    Api Video Games Controller
    public static String GET_URL = BASE_URL+"/api/videogame";
    public static String POST_URL_GAME = BASE_URL+"/api/videogame";
    public static String GET_URL_GAME = BASE_URL+"api/videogame/{id}";
    public static String PUT_URL_GAME = BASE_URL+"api/videogame/{id}";
    public static String DELETE_URL_GAME = BASE_URL+"api/videogame/{id}";



}
