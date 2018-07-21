package com.kotlin.user.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.service.UserService
import com.kotlin.user.service.impl.UserServiceImpl
import javax.inject.Inject

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile:String, verifyCode:String, pwd:String){
        //TODO(业务逻辑)

        userService.register(mobile,verifyCode,pwd)
                .execute(object:BaseSubscriber<Boolean>(){
                    override fun onNext(result: Boolean) {
                        mView.onRegisterResult(result)
                    }
                }, lifecycleProvider)
    }
}