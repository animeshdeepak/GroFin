package com.grofin.base.repo

import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.request.User
import com.grofin.feature.response.RegisterResponse
import io.reactivex.rxjava3.core.Single

interface LoginRegisterRepo {
    fun register(requset: RegisterRequest): Single<RegisterResponse>
}