package Com.Task.newsApp.api

import Com.Task.newsApp.Model.News
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsService {

    @GET("/v2/everything")
    fun getAllNews(@QueryMap parameters : HashMap<String, String>): Deferred<News>
}