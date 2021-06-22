package com.grofin.base.base

import androidx.lifecycle.ViewModel
import com.grofin.base.SharedPrefHelper
import javax.inject.Inject

abstract class BaseViewModel @Inject constructor(
    private val sharedPrefHelper: SharedPrefHelper
) : ViewModel() {

}