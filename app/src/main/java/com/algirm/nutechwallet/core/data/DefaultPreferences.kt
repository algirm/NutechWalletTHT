package com.algirm.nutechwallet.core.data

import android.content.SharedPreferences
import com.algirm.nutechwallet.core.domain.model.UserInfo
import com.algirm.nutechwallet.core.domain.preferences.Preferences
import com.algirm.nutechwallet.core.domain.preferences.Preferences.Companion.KEY_IS_LOGIN
import com.algirm.nutechwallet.core.domain.preferences.Preferences.Companion.KEY_USER_EMAIL
import com.algirm.nutechwallet.core.domain.preferences.Preferences.Companion.KEY_USER_FIRST_NAME
import com.algirm.nutechwallet.core.domain.preferences.Preferences.Companion.KEY_USER_LAST_NAME
import com.algirm.nutechwallet.core.domain.preferences.Preferences.Companion.KEY_USER_TOKEN

class DefaultPreferences(
    private val sharedPref: SharedPreferences
) : Preferences {

    override fun loadIsLogin(): Boolean {
        val token = sharedPref.getString(
            KEY_USER_TOKEN,
            ""
        )
        return !token.isNullOrBlank()
    }

    override fun saveUserToken(token: String) {
        sharedPref.edit()
            .putString(KEY_USER_TOKEN, token)
            .apply()
    }

    override fun loadUserToken(): String {
        return sharedPref.getString(
            KEY_USER_TOKEN,
            null
        ) ?: ""
    }

    override fun saveUserEmail(email: String) {
        sharedPref.edit()
            .putString(KEY_USER_EMAIL, email)
            .apply()
    }

    override fun saveUserFirstName(firstName: String) {
        sharedPref.edit()
            .putString(KEY_USER_FIRST_NAME, firstName)
            .apply()
    }

    override fun saveUserLastName(lastName: String) {
        sharedPref.edit()
            .putString(KEY_USER_LAST_NAME, lastName)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val email = sharedPref.getString(KEY_USER_EMAIL, null)
        val firstName = sharedPref.getString(KEY_USER_FIRST_NAME, null)
        val lastName = sharedPref.getString(KEY_USER_LAST_NAME, null)
        val token = sharedPref.getString(KEY_USER_TOKEN, null)

        return UserInfo(
            email = email ?: "",
            firstName = firstName ?: "",
            lastName = lastName ?: "",
            token = token
        )
    }
}