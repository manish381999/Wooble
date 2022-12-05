package com.wooble.wooble.ui.credentials;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPasswordResponseModel {
    @SerializedName("message")
    @Expose
    String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public ForgetPasswordResponseModel(String message) {
        this.message = message;
    }
}
