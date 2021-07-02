package com.grofin.feature.login

import androidx.databinding.ObservableBoolean
import com.grofin.base.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {
    var errorMobileVisibility = ObservableBoolean()
}