package com.chikeandroid.retrofittutorial2.data.remote;

import com.chikeandroid.retrofittutorial2.data.model.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Chike on 12/3/2016.
 */

public interface APIService {

    @POST("/posts")
    @FormUrlEncoded
    Observable<Post> savePost(@Field("title") String title,
                              @Field("body") String body,
                              @Field("userId") long userId);

    @POST("/posts")
    Observable<Post> savePost(@Body Post post);

    @PUT("/posts/{id}")
    @FormUrlEncoded
    Observable<Post> updatePost(@Path("id") long id,
                          @Field("title") String title,
                          @Field("body") String body,
                          @Field("userId") long userId);

    @DELETE("/posts/{id}")
    Observable<Post> deletePost(@Path("id") long id);

}
