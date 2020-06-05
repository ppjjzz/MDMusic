package com.panjiazhi.mdmusic.common.net

import android.util.Log
import com.panjiazhi.mdmusic.BuildConfig
import com.panjiazhi.mdmusic.common.config.AppConfig
import com.panjiazhi.mdmusic.common.utils.MyPreference
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RetrofitFactory private constructor(){
    val retrofit: Retrofit

    private val cookie: String by MyPreference(AppConfig.APP_COOKIE, "")

    init {
        //打印请求log
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(AppConfig.HTTP_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(headerInterceptor())
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(AppConfig.MUSIC_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
    }
    /**
     * 拦截头部增加token
     */
    private fun headerInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            Log.i("headerInterceptor", cookie)

            if (cookie.isNotEmpty()) {
                val url = request.url.toString()
                request = request.newBuilder()
                    .addHeader("Cookie", cookie)
                    .url(url)
                    .build()
            }

            chain.proceed(request)
        }

    }

    companion object {
        @Volatile
        private var mRetrofitFactory: RetrofitFactory? = null

        private val instance: RetrofitFactory
            get() {
                if (mRetrofitFactory == null) {
                    synchronized(RetrofitFactory::class.java) {
                        if (mRetrofitFactory == null)
                            mRetrofitFactory = RetrofitFactory()
                    }

                }
                return mRetrofitFactory!!
            }

        fun <T> createService(service: Class<T>): T {
            return instance.retrofit.create(service)
        }

        inline fun <reified T> createService(): T = createService(T::class.java)

    }
}