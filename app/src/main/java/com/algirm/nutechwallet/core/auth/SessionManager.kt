package com.algirm.nutechwallet.core.auth

import com.algirm.nutechwallet.core.domain.preferences.Preferences
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val preferences: Preferences
) {

    fun getUserToken(): String = preferences.loadUserToken()

    fun logout() {
        preferences.saveUserToken("")
    }

}