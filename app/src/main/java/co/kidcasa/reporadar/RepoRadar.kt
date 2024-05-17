/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar

import android.app.Application
import co.kidcasa.reporadar.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Custom Application class for initializing global application state.
 */
class RepoRadar : Application() {

    /**
     * Called when the application is starting, before any other application objects have been created.
     * Initializes Koin for dependency injection.
     */
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    /**
     * Initializes Koin with the application context, logging, and modules.
     */
    private fun initKoin() {
        startKoin {
            androidContext(this@RepoRadar)
            androidLogger()
            modules(appModule)
        }
    }
}
