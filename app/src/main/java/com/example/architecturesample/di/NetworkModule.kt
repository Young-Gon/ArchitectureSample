package com.example.architecturesample.di

import com.example.architecturesample.BuildConfig
import com.example.architecturesample.model.network.ImageAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

/**
 * 네트워크 관련 모귤 등록 변수 입니다.
 */
@ExperimentalSerializationApi
val networkModule = module {

	single {

		Retrofit.Builder()
			.baseUrl(BuildConfig.base_url)
			.addConverterFactory( Json{
				if(!BuildConfig.DEBUG)
					ignoreUnknownKeys = true
			}.asConverterFactory("application/json".toMediaType()))
			.client(get())
			.build()
			.create(ImageAPI::class.java)
	}

	single {
		OkHttpClient.Builder().apply {
			cache(Cache(androidApplication().cacheDir, 10 * 1024 * 1024))
			connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
			writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
			readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
			retryOnConnectionFailure(true)
			addInterceptor(HttpLoggingInterceptor().apply {
				if (BuildConfig.DEBUG) {
					level = HttpLoggingInterceptor.Level.BODY
				}
			})
		}.build()
	}
}