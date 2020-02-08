package Com.Task.newsApp.repository

import Com.Task.newsApp.Model.Articles
import Com.Task.newsApp.api.NewsService

class NewsRepository (private val newsService: NewsService){

    private suspend fun getNews(parameters : HashMap<String,String>) =
        newsService.getAllNews(parameters).await()

    suspend fun getAllNewsWithPagination(parameters : HashMap<String,String>): List<Articles?>? {
        if (parameters.isEmpty()) return listOf()

        val request = getNews(parameters)
        return request.articles
    }
}