package com.kalyan

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import di.PlatformConfiguration
import di.PlatformSDK
import com.kalyan.shared.AppRes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PlatformSDK.init(
            PlatformConfiguration(
                activityContext = applicationContext,
                appName = AppRes.string.app_name
            )
        )

        setContent {
            MainView()
        }
    }
}
