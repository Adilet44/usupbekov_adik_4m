package com.example.usupbekov_adik_4m.remote

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Pref (private val context: Context) {

    private val pref:SharedPreferences = context.getSharedPreferences(TASK_PREF_NAME,MODE_PRIVATE)

    fun isUserSeen (): Boolean {
return pref.getBoolean(IS_USER_SEEN_KEY,false)
    }
    fun saveUserSeen(){
        pref.edit().putBoolean(IS_USER_SEEN_KEY,true).apply()
    }

    fun setUser(name: String) {
        pref.edit().putString(NAME_KEY, name).apply()
    }

    fun getUser(): String {
        return pref.getString(NAME_KEY, "").toString()
    }

    fun setImage(image: String) {
        pref.edit().putString(IMAGE_KEY, image).apply()
    }

    fun getImage(): String {
        return pref.getString(IMAGE_KEY, "").toString()
    }

    companion object {
        private val TASK_PREF_NAME = "TaskPref"
        private val IS_USER_SEEN_KEY = "user.seen"
        const val NAME_KEY = "name_key"
        const val IMAGE_KEY = "image_key"
    }
}