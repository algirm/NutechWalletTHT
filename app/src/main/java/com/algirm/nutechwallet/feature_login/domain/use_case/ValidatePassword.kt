package com.algirm.nutechwallet.feature_login.domain.use_case

import android.util.Patterns
import com.algirm.nutechwallet.R
import com.algirm.nutechwallet.feature_login.domain.model.ValidationResult

class ValidatePassword {

    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.error_cant_empty
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}