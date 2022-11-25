package com.wooble.wooble.ui.Blogs;

public class BlogModel {

    String file_id;
    String full_name;
    String title;

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    String content;
    String created_date;
    String image;


    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BlogModel(String file_id, String full_name, String title, String content, String created_date, String image) {
        this.file_id = file_id;
        this.full_name = full_name;
        this.title = title;
        this.content = content;
        this.created_date = created_date;
        this.image = image;
    }




}
