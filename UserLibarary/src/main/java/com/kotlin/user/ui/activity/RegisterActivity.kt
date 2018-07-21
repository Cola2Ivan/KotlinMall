package com.kotlin.user.ui.activity

import android.os.Bundle
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity:BaseMvpActivity<RegisterPresenter>(),RegisterView{


    override fun onRegisterResult(result: Boolean) {
        if (result){
            toast("register success")
        } else {
            toast("register failed")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mEdit.hint = "hello kotlin"

        mBtn.setOnClickListener{
            toast("Hello World")
            mPresenter.register("123","123","123")
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }
}