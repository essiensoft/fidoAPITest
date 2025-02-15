### Step-by-Step Documentation for the Project

This documentation explains the structure, components, and steps to set up and run the project. The project tests API endpoints for a video game database using RestAssured and TestNG.

---

### **1. Project Overview**
- **Goal**: The project automates testing of a REST API that provides endpoints for creating, retrieving, updating, and deleting video game data. This is achieved through a combination of:
  - API payload objects for request/response data (e.g., `VideoGamesDetails`).
  - API endpoints defined as URLs (e.g., `Routes` constants).
  - Test scripts for various scenarios like authentication and CRUD operations on video games.

---

### **2. Project Structure**

**Packages:**

- **`org.fidoTest.api.payload`**: 
  - Contains classes for defining API payload objects. Example: `VideoGamesDetails`.
  
- **`org.fidoTest.api.endpoints`**: 
  - Includes the routes (`Routes.java`) and endpoints implementation (`VideoGamesAPIEndpoints.java`) for interacting with the API.

- **`org.fidoTest.base`**: 
  - Contains reusable test setup (`AbstractTest.java`) for TestNG, including logger initialization, payload creation, and shared variables like `token`.

- **`org.fidoTest.TestCases`**: 
  - Includes TestNG test cases, such as `AuthenticateTC.java` for authentication tests.

---

### **3. Prerequisites**

1. **Software Requirements**:
   - Java Development Kit (JDK 8+).
   - Maven (for dependency management).
   - An IDE (e.g., IntelliJ IDEA, Eclipse).
   
2. **Dependencies**:
   - `RestAssured` (HTTP client library for API testing).
   - `TestNG` (test framework).
   - `Faker` (generating fake test data).
   - `Log4j` (logging framework).

   All dependencies are managed via Maven in the `pom.xml`.

3. **API Prerequisites**:
   - API base URL: `https://www.videogamedb.uk:443`.
   - Authentication Token: Required for protected endpoints. Perform authentication before accessing other APIs.

---

### **4. Configuration**

1. **Routes** (`Routes.java`):
   - Base URL and endpoints are defined in this class. For example:
     - `GET_ALL_GAMES_URL` for fetching all video games.
     - `CREATE_GAME_URL` for creating a video game.

2. **Payloads**: 
   - **`VideoGamesDetails`** is the model for video game data (e.g., `name`, `rating`, `category`).
   - Each API call requires this payload or similar data.

3. **Abstract Test Setup** (`AbstractTest.java`):
   - Defines reusable setup logic, including initializing test payloads and logging.

---

### **5. How to Run the Project**

#### **Step 1: Clone the Project**
- Clone the project repository using Git:
```shell script
git clone <REPO_URL>
  cd <PROJECT_DIRECTORY>
```

#### **Step 2: Install Dependencies**
- Build the project and install dependencies using Maven:
```shell script
mvn clean install
```

#### **Step 3: Execute Test Cases**

##### **Using IDE**:
1. Open the project in an IDE.
2. Navigate to the TestNG XML configuration file (e.g., `testng.xml`).
3. Right-click the XML file and select "Run".

##### **Using Command Line**:
- Execute the following Maven command to run all tests specified in `testng.xml`:
```shell script
mvn test
```

#### **Step 4: Check Results**
- **Logs**: Detailed logging is available in the console or log files (`Log4j`).
- **Reports**: Test results are available in the TestNG default report or any custom configured reporter (e.g., Extent Reports).

---

### **6. Key Project Features**

#### **6.1 Authentication Test (`AuthenticateTC.java`)**
- This test verifies the API's `authenticate` endpoint, performing the following validations:
  - Checks that the response status is `200`.
  - Confirms a valid authentication token is received.
  - Verifies that the content type is `application/json`.
  - Ensures the response time is under 2 seconds.

Run:
```java
@Test
public void testAuthenticateUser();
```

#### **6.2 Video Game CRUD Endpoints (`VideoGamesAPIEndpoints.java`)**
1. **Fetch All Video Games**:
   - Endpoint: `GET_ALL_GAMES_URL`.
   - Method: `fetchAllVideoGames()`.

2. **Create a Video Game**:
   - Endpoint: `CREATE_GAME_URL`.
   - Method: `createVideoGame(VideoGamesDetails gameDetails)`.

3. **Fetch Video Game by ID**:
   - Endpoint: `GET_GAME_BY_ID_URL`.
   - Method: `getVideoGame(VideoGamesDetails gameDetails)`.

4. **Update Video Game**:
   - Endpoint: `UPDATE_GAME_URL`.
   - Method: `updateVideoGame(VideoGamesDetails gameDetails)`.

5. **Delete Video Game**:
   - Endpoint: `DELETE_GAME_URL`.
   - Method: `deleteVideoGame(VideoGamesDetails gameDetails)`.

---

### **7. Troubleshooting Notes**

1. **Connection Issues**:
   - Verify the API URL (`BASE_URL`) in `Routes.java`.
   - Ensure the API server is running and reachable.

2. **Authentication Failures**:
   - Check the `DEFAULT_USERNAME` and `DEFAULT_PASSWORD` in `AbstractTest.java`.
   - Confirm the correct token is being passed for authenticated requests.

3. **Test Failures**:
   - Investigate API response codes and logs in the console/log files.
   - Ensure the payload matches API requirements.

---

### **8. Future Enhancements**

1. **Extend Scope**:
   - Add tests for edge cases and error scenarios.
   - Cover additional endpoints (if available).

2. **Parameterize Tests**:
   - Use TestNG's `@DataProvider` for better coverage with multiple data sets.

3. **Improved Reporting**:
   - Integrate with custom reporting tools like Allure or Extent Reports.

4. **CI/CD Integration**:
   - Automate tests with Jenkins/GitHub Actions.

---
