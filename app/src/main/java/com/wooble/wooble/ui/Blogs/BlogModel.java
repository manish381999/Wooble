package com.wooble.wooble.ui.Blogs;

public class BlogModel {

    String id;
    String title;
    String content;
    String created_date;
    String image;


    public BlogModel(String id, String title, String content, String created_date, String image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created_date = created_date;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
