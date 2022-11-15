package com.wooble.wooble.ui.Blogs;

import com.wooble.wooble.ui.Resume.ResumeModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiInterface {

    @FormUrlEncoded
    @POST("blog.php?apicall=insertblogdata")
    Call<ResponseModel> insertBlog(
                        @Field("email_id") String email_id,
                        @Field("title") String title,
                        @Field("content") String content,
                        @Field("pic") String image,
                        @Field("name") String name);

    @FormUrlEncoded
    @POST("blog.php?apicall=getblogdata")
    Call<ArrayList<BlogModel>> getAllBlogs(
            @Field("email_id") String email_id
    );

    @FormUrlEncoded
    @POST("resume.php?apicall=insertresumedata")
    Call<ResponseModel> insertResume(
            @Field("email_id") String email_id,
            @Field("title") String title,
            @Field("resume") String resume
    );

    @FormUrlEncoded
    @POST("resume.php?apicall=getresumedata")
    Call<ArrayList<ResumeModel>> getResume(
            @Field("email_id") String email_id
    );
}
