package prepare;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserApi extends HttpClient {

    private static final String PATH_REGISTER_USER = "api/auth/register";
    private static final String PATH_LOGIN_USER = "api/auth/login";
    private static final String PATH_PROFILE_USER = "api/auth/user";
    private ValidatableResponse response;

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(ModelUser user){
        return doPostRequest(PATH_LOGIN_USER,user);
    }

    @Step("Удалить пользователя")
    public ValidatableResponse deleteUser(String token){
        return doDeleteRequest(PATH_PROFILE_USER,token);
    }

    public String clearToken(String token){
        return token.substring("Bearer ".length());
    }

    @Step
    public String loginAndGetUserToken(ModelUser newUser){
        response = loginUser(newUser);
        response.assertThat().statusCode(200);
        String token = response.extract().jsonPath().getString("accessToken");
        return clearToken(token);
    }

    @Step
    public void deleteUserAndCheck(String token){
        response = deleteUser(token);
        response.assertThat().statusCode(202);
        String message = response.extract().jsonPath().getString("message");
        assertEquals("User successfully removed", message);
    }

    @Step("Удалить пользователя без переданного токена")
    public void deleteUserWithoutToken(ModelUser user){
        String token =  loginAndGetUserToken(user);
        deleteUserAndCheck(token);
    }
}
