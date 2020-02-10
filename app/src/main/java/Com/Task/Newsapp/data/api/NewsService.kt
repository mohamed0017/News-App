package Com.Task.newsApp.data.api

import Com.Task.newsApp.data.Model.News
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsService {

    @GET("/v2/everything")
    fun getAllNewsAsync(@QueryMap parameters: HashMap<String, String>): Deferred<News>
}