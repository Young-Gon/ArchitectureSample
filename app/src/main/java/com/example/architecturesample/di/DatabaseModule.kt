package com.example.architecturesample.di

import androidx.room.Room
import com.example.architecturesample.model.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


/**
 * 룸 데이터베이스 관련 모듈 등록 변수 입니다
 */
val roomModule = module {
		single {
			Room.databaseBuilder(
				androidApplication(),
				AppDatabase::class.java,
				"Image_Database"
			).build()
		}
		single {  get<AppDatabase>().getImageDataDao() }
	}