package com.grofin.base

import android.content.SharedPreferences
import com.grofin.base.constants.SharedPrefKeys
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun saveToken(token: String?) = sharedPreferences.edit().putString(SharedPrefKeys.KEY_SHARED_PREF, token).apply()

    fun getToken(): String? = sharedPreferences.getString(SharedPrefKeys.KEY_SHARED_PREF, "")
}