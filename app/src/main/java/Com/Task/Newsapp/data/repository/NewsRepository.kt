package Com.Task.newsApp.data.repository

import Com.Task.newsApp.data.Model.Article
import Com.Task.newsApp.data.api.NewsService

class NewsRepository(private val newsService: NewsService) {

    private suspend fun getNews(parameters: HashMap<String, String>) =
        newsService.getAllNewsAsync(parameters).await()

    suspend fun getAllNewsWithPagination(parameters: HashMap<String, String>): List<Article?>? {
        if (parameters.isEmpty()) return listOf()

        val request = getNews(parameters)
        return request.articles
    }
}