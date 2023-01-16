package com.jpoveda.menu2

class Application : android.app.Application() {
    companion object {
        lateinit var preferences: Preference
    }

    override fun onCreate() {
        super.onCreate()
        preferences = Preference(applicationContext)
    }
}

