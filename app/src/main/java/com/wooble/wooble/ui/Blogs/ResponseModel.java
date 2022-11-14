package com.wooble.wooble.ui.Blogs;

public class ResponseModel {

    String error;
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
