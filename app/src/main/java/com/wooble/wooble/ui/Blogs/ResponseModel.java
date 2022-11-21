package com.wooble.wooble.ui.Blogs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseModel {

    @SerializedName("error")
    @Expose
    String error;
    @SerializedName("message")
    @Expose
    String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseModel(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
