package com.yangyongwen.zhihu3.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yangyongwen on 16/5/5.
 */
@Singleton
public class SharePreferenceUtils {
    private static final String ZHIHU_APP_PREFERENCE = "Zhihu3";

    Context mContext;




    @Inject
    public SharePreferenceUtils(Context context){
        this.mContext=context;
    }

    public void putLongValue(String key, long n) {

        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, n);
        editor.apply();
    }

    public long getLongValue(String key) {
        SharedPreferences pref = mContext.getSharedPreferences(ZHIHU_APP_PREFERENCE,
                Context.MODE_PRIVATE);
        return pref.getLong(key, -1);
    }

    public long getLongValue(String key, long mDeafult) {
        SharedPreferences pref = mContext.getSharedPreferences(ZHIHU_APP_PREFERENCE,
                Context.MODE_PRIVATE);
        return pref.getLong(key, mDeafult);
    }

    public void putIntValue(String key, int n) {
        SharedPreferences pref = mContext.getSharedPreferences(ZHIHU_APP_PREFERENCE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, n);
        editor.apply();
    }

    public int getIntValue(String key) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE,
                        Context.MODE_PRIVATE);
        return pref.getInt(key, -1);
    }

    public int getIntValue(String key, int mDeafult) {
        SharedPreferences pref = mContext.getSharedPreferences(ZHIHU_APP_PREFERENCE,
                Context.MODE_PRIVATE);
        return pref.getInt(key, mDeafult);
    }

    public void putStringValue(String key, String s) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE,
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, s);
        editor.apply();
    }

    public String getStringValue(String key) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

    public String getStringValue(String key, String defaultValue) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getString(key, defaultValue);
    }

    public void putBooleanValue(String key, Boolean b) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, b);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }

    public boolean getBooleanValue(String key, boolean mDefault) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getBoolean(key, mDefault);
    }

    public void putFloatValue(String key, float f) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(key, f);
        editor.apply();
    }

    public float getFloatValue(String key) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getFloat(key, 0.0f);
    }

    public float getFloatValue(String key, float mDefault) {
        SharedPreferences pref = mContext.getSharedPreferences(ZHIHU_APP_PREFERENCE,
                Context.MODE_PRIVATE);
        return pref.getFloat(key, mDefault);
    }

    public void putStringSet(String key, Set<String> s) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key, s);
        editor.apply();
    }

    public Set<String> getStringSet(String key) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getStringSet(key, null);
    }

    public void remove(String key) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }

    public boolean isKeyExist(String key) {
        SharedPreferences pref = mContext.
                getSharedPreferences(ZHIHU_APP_PREFERENCE, Context.MODE_PRIVATE);
        return pref.contains(key);
    }
}
