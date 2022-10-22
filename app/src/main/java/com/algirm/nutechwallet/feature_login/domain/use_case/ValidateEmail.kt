package com.algirm.nutechwallet.feature_login.domain.use_case

import android.util.Patterns
import com.algirm.nutechwallet.R
import com.algirm.nutechwallet.feature_login.domain.model.ValidationResult

class ValidateEmail {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.error_cant_empty
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.error_invalid_email
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}