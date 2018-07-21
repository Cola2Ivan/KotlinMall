package com.kotlin.base.data.net

import com.kotlin.base.commom.BaseConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor(){
    companion object {
        val instance:RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit:Retrofit
    private val interceptor:Interceptor

    init {
        interceptor = Interceptor {
            chain ->
                val request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type","application/json")
                        .addHeader("Charset","utf-8")
                        .build()
    chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstants.server_address)
                .addConverterFactory(GsonConverterFactory.create()) //返回的数据进行Gson解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //对Call进行RxJava适配
                .client(createClient())
                .build()

    }

    private fun createClient(): OkHttpClient{
        return OkHttpClient.Builder()
                .addInterceptor(initLogIntercepter())
                .addInterceptor(interceptor)
                .connectTimeout(10,TimeUnit.MILLISECONDS)
                .readTimeout(10,TimeUnit.MILLISECONDS)
                .build()
    }

    private fun initLogIntercepter(): Interceptor? {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }
}