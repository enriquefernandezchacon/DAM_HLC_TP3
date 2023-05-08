package com.example.examenretrofitbase.service;

import com.example.examenretrofitbase.modelo.NewPost;
import com.example.examenretrofitbase.modelo.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IPostService {

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int id);

    @POST("posts")
    Call<Post> addPost(@Body NewPost newPost);
}
