package com.grofin.feature.webview

import androidx.databinding.ObservableBoolean
import com.grofin.base.base.BaseViewModel
import javax.inject.Inject

class WebViewViewModel @Inject constructor() : BaseViewModel() {
    val retryBtnVisibility = ObservableBoolean(false)
    val loaderVisibility = ObservableBoolean(false)
}