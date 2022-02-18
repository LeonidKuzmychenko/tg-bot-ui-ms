package com.example.tgserialsbot.http;

import com.example.tgserialsbot.http.model.Example;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

import java.util.concurrent.CompletableFuture;

public interface TestService {

    @GET("/todos/1")
    CompletableFuture<Response<Example>> get1();

    @GET("/todos/1")
    CompletableFuture<Example> get2();

    @GET("/todos/1")
    Response<Example> get3();//not work

    @GET("/todos/1")
    Example get4();//not work

    @GET("/todos/1")
    Call<Example> get5();

}