package com.wooble.wooble.ui.Gallery;

public class Gallery {
    public String image_url;
    public  String title;
    public String caption;


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Gallery(String image_url, String title, String caption) {
        this.image_url = image_url;
        this.title = title;
        this.caption = caption;
    }
}
