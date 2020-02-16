package com.example.computerdatabase

import android.app.Application
import com.example.computerdatabase.di.AppComponent
import com.example.computerdatabase.di.AppContextModule
import com.example.computerdatabase.di.DaggerAppComponent

class App: Application() {

    private lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }

    fun getAppComponent(): AppComponent {
        if (!::mAppComponent.isInitialized) {
            mAppComponent = DaggerAppComponent
                .builder()
                .appContextModule(AppContextModule(this))
                .build()
        }
        return mAppComponent
    }

}