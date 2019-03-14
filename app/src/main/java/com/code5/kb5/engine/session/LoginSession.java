package com.code5.kb5.engine.session;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginSession {
    private static final String PREF_NAME = "LOGIN_SESSION";

    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String IS_LOGIN = "IS_LOGIN";

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LoginSession(Context context) {
        this.context = context;
    }

    public void setLoginSession(String key, String value){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putString(key, value);
        editor.apply();
        editor.commit();
    }

    public void setIsLogin(String key, boolean value){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putBoolean(key, value);
        editor.apply();
        editor.commit();
    }

    public String getLoginSession(String key){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(key, "");
    }

    public boolean isLogin(String key){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(key, false);
    }

    public void removeItemLoginSession(String key){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.remove(key);
        editor.apply();
        editor.commit();
    }

    public void removeLoginSession(){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
        editor.commit();
    }
}
