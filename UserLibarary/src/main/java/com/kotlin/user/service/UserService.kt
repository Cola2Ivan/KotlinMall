package com.kotlin.user.service

import rx.Observable
import java.util.*

interface UserService {
    fun register(mobile:String , verifyCode:String, pwd:String):Observable<Boolean>
}