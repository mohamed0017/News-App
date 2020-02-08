package Com.Task.newsApp.datasource

import Com.Task.newsApp.Model.Articles
import Com.Task.newsApp.api.NetworkState
import Com.Task.newsApp.repository.NewsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.*

class NewsDataSource(
    private val repository: NewsRepository,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Articles>() {

    private var supervisorJob = SupervisorJob()
    private val networkState = MutableLiveData<NetworkState>()
    private var retryQuery: (() -> Any)? = null //Keep the last query just in case it fails

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Articles>) {
        retryQuery = { loadInitial(params, callback) }
        getNews(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
        val page = params.key
        retryQuery = { loadAfter(params, callback) }
        getNews(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
    }

    private fun getNews(
        page: Int,
        callback: (List<Articles>) -> Unit
    ) {
        networkState.postValue(NetworkState.RUNNING)
        scope.launch(getJobErrorHandler() + supervisorJob) {
            val news = repository.getAllNewsWithPagination(getParameters(page))
            retryQuery = null
            networkState.postValue(NetworkState.SUCCESS)
            callback(news as List<Articles>)
            // TODO ...
        }
    }

    private fun getParameters(page: Int) : HashMap<String,String> {
        val parameters = HashMap<String,String>()
        parameters["page"] = "$page"
        parameters["q"] = "all"
        parameters["apiKey"] = "b76e4e130f9e4b929eba5da73c28b9d6"
        return parameters
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, _ ->
        networkState.postValue(NetworkState.FAILED)
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()
    }

    fun getNetworkState(): LiveData<NetworkState> =
        networkState

    fun refresh() =
        this.invalidate()

    fun retryFailedQuery() {
        val prevQuery = retryQuery
        retryQuery = null
        prevQuery?.invoke()
    }
}