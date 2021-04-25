package com.geo.sampleapplication.di


import android.content.Context
import com.geo.sampleapplication.network.MyApi
import com.geo.sampleapplication.network.safe.Connectivity
import com.geo.sampleapplication.util.Const.BASE_URL
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideConnectivity(@ApplicationContext context: Context): Connectivity {
        return Connectivity(context)
    }


    @Singleton
    @Provides
    fun provideOkHttp(connectivity: Connectivity): OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(8000 , TimeUnit.SECONDS)
            .writeTimeout(8000 , TimeUnit.SECONDS)
            .connectTimeout(1 , TimeUnit.MINUTES)
                .addInterceptor(connectivity)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(client: Lazy<OkHttpClient>): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .callFactory { request ->
                // this bellow fun ,called in background thread
                client.get().newCall(request)
            }
            .build()
    }

    @Provides
    fun provideMyApi(retrofit: Retrofit): MyApi {
        return retrofit.create(MyApi::class.java)
    }




}