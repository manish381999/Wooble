package com.wooble.wooble.ui.Blogs;

import com.wooble.wooble.ui.portfolio.EndPoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    private  static Controller clientobject;
    private static Retrofit retrofit;
    public Controller() {
        retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Controller getInstance(){

        if(clientobject==null)
        {
            clientobject = new Controller();
        }
        return clientobject;
    }

    apiInterface getApiInterface(){
        return  retrofit.create(apiInterface.class);
    }
}
