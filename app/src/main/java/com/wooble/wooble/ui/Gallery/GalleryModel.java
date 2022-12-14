package com.wooble.wooble.ui.Gallery;

public class GalleryModel {

    public  String id;
    public String image_url;
    public  String title;
    public String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GalleryModel(String id, String image_url, String title, String description) {
        this.id = id;
        this.image_url = image_url;
        this.title = title;
        this.description = description;
    }
}
