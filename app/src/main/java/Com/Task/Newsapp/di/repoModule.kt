package Com.Task.newsApp.di

import Com.Task.newsApp.data.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { NewsRepository(get()) }
}