package com.wooble.wooble;

import android.content.Context;
import android.content.SharedPreferences;
public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user){
        //save session of user whenever user is logged in
        String email = user.getEmail();

        editor.putString(SESSION_KEY,email).commit();
    }

    public String getSession(){
        //return user id whose session is saved
        return sharedPreferences.getString(SESSION_KEY, null);
    }

    public void removeSession(){
        editor.putString(SESSION_KEY,null).commit();
    }
}
