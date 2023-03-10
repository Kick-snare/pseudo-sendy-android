package com.uzun.pseudosendy

import android.app.Application
import com.naver.maps.map.NaverMapSdk
import com.uzun.pseudosendy.BuildConfig.NAVER_MAP_CLIENT_ID
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PseudoSendyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(NAVER_MAP_CLIENT_ID)
    }
}