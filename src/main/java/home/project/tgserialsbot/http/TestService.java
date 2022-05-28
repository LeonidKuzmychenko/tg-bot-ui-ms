package home.project.tgserialsbot.http;

import home.project.tgserialsbot.bot.model.BotUser;
import home.project.tgserialsbot.http.model.SearchByNameResponse;
import home.project.tgserialsbot.http.model.UserUpdateRequest;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

public interface TestService {

//    @GET("/todos/1")
//    CompletableFuture<Response<Example>> get1();
//
//    @GET("/todos/1")
//    CompletableFuture<Example> get2();
//
//    @GET("/todos/1")
//    Response<Example> get3();//not work
//
//    @GET("/todos/1")
//    Example get4();//not work
//
//    @GET("/todos/1")
//    Call<Example> get5();

    @GET("/v1/user")
    CompletableFuture<Response<BotUser>> getUser(@Query("chatId") String chatId);

    @PUT("/v1/user")
    CompletableFuture<Response<BotUser>> updateUser(@Body UserUpdateRequest userUpdateRequest);

    @GET("/v1/serial")
    CompletableFuture<Response<SearchByNameResponse>> getSerialByName(@Query("name") String name);
}