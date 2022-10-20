package com.algirm.nutechwallet.core.domain.preferences

import com.algirm.nutechwallet.core.domain.model.UserInfo

interface Preferences {

    fun saveIsLogin(isLogin: Boolean)
    fun loadIsLogin(): Boolean

    fun saveUserToken(token: String)
    fun loadUserToken(): String

    fun saveUserEmail(email: String)
    fun saveUserFirstName(firstName: String)
    fun saveUserLastName(lastName: String)
    fun loadUserInfo(): UserInfo

    companion object {
        const val KEY_IS_LOGIN = "is_login"
        const val KEY_USER_TOKEN = "user_token"
        const val KEY_USER_EMAIL = "user_email"
        const val KEY_USER_FIRST_NAME = "user_first_name"
        const val KEY_USER_LAST_NAME = "user_last_name"
        const val KEY_USER_PASSWORD = "user_password"
    }
}