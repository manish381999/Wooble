package com.wooble.wooble.ui.Blogs;

public class BlogModel {

    String blog_id;
    String email_id;
    String title;
    String content;
    String last_updated;

    public String getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(String blog_id) {
        this.blog_id = blog_id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
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

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public BlogModel(String blog_id, String email_id, String title, String content, String last_updated) {
        this.blog_id = blog_id;
        this.email_id = email_id;
        this.title = title;
        this.content = content;
        this.last_updated = last_updated;
    }
}
