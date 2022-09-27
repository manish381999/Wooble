package com.wooble.wooble;

import android.content.Context;
import android.content.SharedPreferences;
public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_EMAIL = "session_email";
    String SESSION_PASSWORD = "session_password";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user){
        //save session of user whenever user is logged in
        String email = user.getEmail();
        String password = user.getPassword();

        editor.putString(SESSION_EMAIL,email).commit();
        editor.putString(SESSION_PASSWORD,password).commit();

    }

    public String getSessionEmail(){

        return sharedPreferences.getString(SESSION_EMAIL, null) ;

    }
    public String getSessionPassword(){

        return sharedPreferences.getString(SESSION_PASSWORD, null);

    }

    public void removeSession(){
        editor.putString(SESSION_EMAIL,null).commit();
        editor.putString(SESSION_PASSWORD, null).commit();
    }
}
