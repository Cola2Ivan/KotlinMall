package com.kotlin.base.injection.module

import android.app.Activity
import com.kotlin.base.injection.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule (private var activity:Activity){

    @ActivityScope
    @Provides
    fun providerActivity():Activity{
        return activity
    }
}