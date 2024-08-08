package itapeviprev.cursoandroid.com.itapeviprev

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ItapeviPrevApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}