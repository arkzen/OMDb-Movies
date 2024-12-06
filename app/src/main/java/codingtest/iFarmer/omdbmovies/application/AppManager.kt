package codingtest.iFarmer.omdbmovies.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class AppManager  @Inject constructor(): Application() {

    init {
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

    }
    companion object {
        private var instance: AppManager? = null

        fun appContext(): AppManager {
            return instance as AppManager
        }
    }
}