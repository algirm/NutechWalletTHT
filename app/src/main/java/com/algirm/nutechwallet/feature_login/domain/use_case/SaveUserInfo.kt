package com.algirm.nutechwallet.feature_login.domain.use_case

import android.util.Patterns
import com.algirm.nutechwallet.R
import com.algirm.nutechwallet.core.domain.model.UserInfo
import com.algirm.nutechwallet.core.domain.preferences.Preferences
import com.algirm.nutechwallet.feature_login.domain.model.ValidationResult

class SaveUserInfo(private val preferences: Preferences) {

    operator fun invoke(userInfo: UserInfo) {
        preferences.saveUserToken(userInfo.token?: "")
        preferences.saveUserEmail(userInfo.email)
        preferences.saveUserFirstName(userInfo.firstName?: "")
        preferences.saveUserLastName(userInfo.lastName?: "")
    }
}