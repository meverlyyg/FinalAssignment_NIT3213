package com.vu.androidbasicapp.home.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class RestfulApiDevRetrofitClient @Inject constructor() {

    private val BASE_URL = "https://nit3213-api-h2b3-latest.onrender.com/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    //Retrofit can automatically convert JSON responses into Java objects (and vice versa) using
    //converters like Gson or Moshi. This eliminates the need for manual parsing
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    val apiService: RestfulApiDevService = retrofit.create(RestfulApiDevService::class.java)
}