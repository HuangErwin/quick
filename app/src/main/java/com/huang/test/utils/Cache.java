package com.huang.test.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Base64;


import com.huang.test.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述：SharedPreferences工具类
 * @author LeiJue
 */
public final class Cache {

    @NonNull
    public static Cache newInstance() {
        return new Cache();
    }

    @NonNull
    public static Cache newInstance(String name) {
        return new Cache(name);
    }

    @NonNull
    public static Cache newInstance(String name, int mode) {
        return new Cache(name, mode);
    }

    private boolean defaultBoolean;
    private int defaultInt;
    private long defaultLong;
    private float defaultFloat;
    private String defaultString;
    private Set<String> defaultSet;

	private SharedPreferences shared;

	public Cache() {
	    this(MyApplication.Companion.getContext().getPackageName());
    }

	public Cache(String name) {
        this(name, Context.MODE_PRIVATE);
    }

    public Cache(String name, int mode) {
        this.defaultBoolean = false;
        this.defaultInt = 0;
        this.defaultLong = 0;
        this.defaultFloat = 0;
        this.defaultString = "";
        this.defaultSet = new HashSet<>();
        this.shared = MyApplication.Companion.getContext().getSharedPreferences(name, mode);
        defaultSet.add("");
    }

    public Cache put(String key, boolean value) {
        shared.edit().putBoolean(key, value).apply();
        return this;
    }

    public Cache put(String key, int value) {
        shared.edit().putInt(key, value).apply();
        return this;
    }

    public Cache put(String key, long value) {
        shared.edit().putLong(key, value).apply();
        return this;
    }

    public Cache put(String key, float value) {
        shared.edit().putFloat(key, value).apply();
        return this;
    }

    public Cache put(String key, String value) {
        shared.edit().putString(key, value).apply();
        return this;
    }

    public Cache put(String key, Set<String> value) {
        shared.edit().putStringSet(key, value).apply();
        return this;
    }

    public Cache put(String key, Serializable value) {
        ByteArrayOutputStream aOs = new ByteArrayOutputStream();
        ObjectOutputStream bOs;
        try {
            bOs = new ObjectOutputStream(aOs);
            bOs.writeObject(value);
            String objectVal = new String(Base64.encode(
                    aOs.toByteArray(), Base64.NO_WRAP));
            shared.edit().putString(key, objectVal).apply();
            bOs.close();
            aOs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean getBoolean(String key) {
        return shared.getBoolean(key, defaultBoolean);
    }

	public int getInt(String key) {
	    return shared.getInt(key, defaultInt);
    }

    public long getLong(String key) {
	    return shared.getLong(key, defaultLong);
    }

    public Float getFloat(String key) {
	    return shared.getFloat(key, defaultFloat);
    }

    public String getString(String key) {
	    return shared.getString(key, defaultString);
    }

    public Set<String> getStringSet(String key) {
        return shared.getStringSet(key, defaultSet);
    }

    public Serializable getSerializable(String key) {
        Serializable obj = null;
        try {
            String temp = shared.getString(key, "");
            byte[] buffer = Base64.decode(temp, Base64.NO_WRAP);
            ByteArrayInputStream bIn = new ByteArrayInputStream(buffer);

            ObjectInputStream oIn = null;
            if (bIn.available() != 0) {
                oIn = new ObjectInputStream(bIn);
                obj = (Serializable) oIn.readObject();
            }
            if (oIn != null) {
                oIn.close();
            }
            bIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Cache setDefaultBoolean(boolean defaultBoolean) {
        this.defaultBoolean = defaultBoolean;
        return this;
    }

    public Cache setDefaultInt(int defaultInt) {
        this.defaultInt = defaultInt;
        return this;
    }

    public Cache setDefaultFloat(float defaultFloat) {
        this.defaultFloat = defaultFloat;
        return this;
    }

    public Cache setDefaultLong(long defaultLong) {
        this.defaultLong = defaultLong;
        return this;
    }

    public Cache setDefaultString(String defaultString) {
        this.defaultString = defaultString;
        return this;
    }

    public Cache setDefaultSet(Set<String> defaultSet) {
	    this.defaultSet = defaultSet;
        return this;
    }

    public void remove(String key) {
        shared.edit().remove(key).apply();
    }

    public void removeAll() {
        shared.edit().clear().apply();
    }

}
