package com.wooble.wooble.ui.Blogs;

import com.wooble.wooble.ui.Project.ProjectModel;
import com.wooble.wooble.ui.Resume.ResumeModel;
import com.wooble.wooble.ui.portfolio.EndPoints;

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
            @Field("pic[]") ArrayList<String> image);


    @FormUrlEncoded
    @POST("blog.php?apicall=getblogdata")
    Call<ArrayList<BlogModel>> getAllBlogs(
            @Field("email_id") String email_id
    );

    @FormUrlEncoded
    @POST("blog.php?apicall=deleteblogdata")
    Call<ResponseModel> deleteBlog(
            @Field("file_id") String file_id
    );

    @FormUrlEncoded
    @POST(EndPoints.INSERT_RESUME_DATA)
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

    @FormUrlEncoded
    @POST("project.php?apicall=insertprojectdata")
    Call<ResponseModel> insertProject(
            @Field("email_id") String email_id,
            @Field("project_name") String project_name,
            @Field("aim_of_project") String aim_of_project,
            @Field("description") String description,
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

    @FormUrlEncoded
    @POST("project.php?apicall=getprojectdata")
    Call<ArrayList<ProjectModel>> getProject(
            @Field("email_id") String email_id
    );

    @FormUrlEncoded
    @POST("project.php?apicall=deleteprojectdata")
    Call<ResponseModel> deleteProject(
            @Field("email_id") String email_id,
            @Field("file_id") String file_id
    );

    @FormUrlEncoded
    @POST("project.php?apicall=updateprojectdata")
    Call<ResponseModel> updateProject(
            @Field("file_id") String file_id,
            @Field("email_id") String email_id,
            @Field("project_name") String project_name,
            @Field("aim_of_project") String aim_of_project,
            @Field("description") String description,
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
