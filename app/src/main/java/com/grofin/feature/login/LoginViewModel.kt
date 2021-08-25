package com.grofin.feature.login

import androidx.databinding.ObservableBoolean
import com.grofin.base.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {
    // for login screen
    var errorMobileVisibility = ObservableBoolean()
    // for register screen
    var errorMobileVisibilityRegister = ObservableBoolean()
    var errorReferralVisibility = ObservableBoolean()
}