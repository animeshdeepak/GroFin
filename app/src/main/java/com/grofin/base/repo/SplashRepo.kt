package com.grofin.base.repo

import com.grofin.request.User
import io.reactivex.rxjava3.core.Single

interface SplashRepo {
    fun getSingleUser(): Single<User>
}