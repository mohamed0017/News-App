package Com.Task.newsApp

import Com.Task.newsApp.di.networkModule
import Com.Task.newsApp.di.repositoryModule
import Com.Task.newsApp.di.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@NewsApp)
            modules(listOf(viewModelModule, networkModule, repositoryModule))
        }
    }
}