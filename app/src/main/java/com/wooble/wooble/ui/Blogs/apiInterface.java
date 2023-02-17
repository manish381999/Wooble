package com.wooble.wooble.ui.Blogs;

import com.wooble.wooble.ui.Work.ProjectModel;
import com.wooble.wooble.ui.Resume.ResumeModel;
import com.wooble.wooble.ui.portfolio.EndPoints;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiInterface {

    @FormUrlEncoded
    @POST("blog.php?apicall=publishblog")
    Call<ResponseModel> publishBlog(
            @Field("blog_id") String blog_id,
            @Field("email_id") String email_id,
            @Field("title") String title,
            @Field("content") String content);

    @FormUrlEncoded
    @POST("blog.php?apicall=publishdraft")
    Call<ResponseModel> publishDraft(
            @Field("blog_id") String blog_id,
            @Field("email_id") String email_id,
            @Field("title") String title,
            @Field("content") String content);



    @FormUrlEncoded
    @POST("blog.php?apicall=startblog")
    Call<ResponseModel> startBlog(
            @Field("email_id") String email_id
    );

    @FormUrlEncoded
    @POST("blog.php?apicall=uploadblogimage")
    Call<ResponseModel> uploadBlogImage(
            @Field("blog_id") String blog_id,
            @Field("email_id") String email_id,
            @Field("image") String image

    );

    @FormUrlEncoded
    @POST("blog.php?apicall=uploadblogvideo")
    Call<ResponseModel> uploadBlogVideo(
            @Field("blog_id") String blog_id,
            @Field("email_id") String email_id,
            @Field("video") String video

    );

    @FormUrlEncoded
    @POST("blog.php?apicall=uploadblogaudio")
    Call<ResponseModel> uploadBlogAudio(
            @Field("blog_id") String blog_id,
            @Field("email_id") String email_id,
            @Field("audio") String audio

    );

    @FormUrlEncoded
    @POST("blog.php?apicall=deleterow")
    Call<ResponseModel> deleteRow(
            @Field("blog_id") String blog_id,
            @Field("email_id") String email_id
    );

    @FormUrlEncoded
    @POST("blog.php?apicall=getblogdata")
    Call<ArrayList<BlogModel>> getAllBlogs(
            @Field("email_id") String email_id
    );


    @FormUrlEncoded
    @POST(EndPoints.INSERT_RESUME_DATA)
    Call<ResponseModel> insertResume(
            @Field("email_id") String email_id,
            @Field("resume") String resume
    );

    @FormUrlEncoded
    @POST("resume.php?apicall=getresumedata")
    Call<ArrayList<ResumeModel>> getResume(
            @Field("email_id") String email_id
    );

    @FormUrlEncoded
    @POST("project.php?apicall=insertprojectdata")
    Call<ResponseModel> insertProject(
            @Field("work_title") String project_name,
            @Field("work_description") String description,
            @Field("email_id") String email_id,
            @Field("image_1") String image_1,
            @Field("image_2") String image_2,
            @Field("image_3") String image_3,
            @Field("image_4") String image_4,
            @Field("image_5") String image_5,
            @Field("image_6") String image_6,
            @Field("video") String video,
            @Field("conclusion") String conclusion,
            @Field("project_pdf") String project_pdf,
             @Field("aim_of_work") String aim_of_project

    );

    @FormUrlEncoded
    @POST("project.php?apicall=getprojectdata")
    Call<ArrayList<ProjectModel>> getProject(
            @Field("email_id") String email_id
    );

    @FormUrlEncoded
    @POST("project.php?apicall=deleteprojectdata")
    Call<ResponseModel> deleteProject(
            @Field("email_id") String email_id,
            @Field("entry_id") String file_id
    );

    @FormUrlEncoded
    @POST("project.php?apicall=updateprojectdata")
    Call<ResponseModel> updateProject(
            @Field("entry_id") String file_id,
            @Field("email_id") String email_id,
            @Field("work_title") String project_name,
            @Field("aim_of_work") String aim_of_project,
            @Field("work_description") String description,
            @Field("image_1") String image_1,
            @Field("image_2") String image_2,
            @Field("image_3") String image_3,
            @Field("image_4") String image_4,
            @Field("image_5") String image_5,
            @Field("image_6") String image_6,
            @Field("video") String video,
            @Field("project_pdf") String project_pdf,
            @Field("conclusion") String conclusion
    );
}
