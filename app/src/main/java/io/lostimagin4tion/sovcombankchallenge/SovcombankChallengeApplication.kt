package io.lostimagin4tion.sovcombankchallenge

import android.app.Application
import android.os.StrictMode
import io.lostimagin4tion.sovcombankchallenge.dagger.AppComponent
import io.lostimagin4tion.sovcombankchallenge.dagger.DaggerAppComponent
import timber.log.Timber

class SovcombankChallengeApplication: Application() {

    override fun onCreate() {
        initStrictMode()
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .context(this)
            .application(this)
            .build()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectActivityLeaks()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDropBox()
                    .build()
            )

            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyDropBox()
                    .penaltyLog()
                    .build()
            )
        }
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}