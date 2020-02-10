package Com.Task.newsApp.ui.news

import Com.Task.newsApp.data.api.NetworkState
import Com.Task.newsApp.base.BaseViewModel
import Com.Task.newsApp.datasource.NewsDataSourceFactory
import Com.Task.newsApp.data.repository.NewsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class NewsViewModel(newsRepository: NewsRepository) : BaseViewModel(){

    private val recipeDataSource = NewsDataSourceFactory(repository = newsRepository, scope = ioScope)

    val news = LivePagedListBuilder(recipeDataSource, pagedListConfig()).build()
    val networkState: LiveData<NetworkState>? =
        Transformations.switchMap(recipeDataSource.source) { it.getNetworkState() }

    fun loadInitialNews(){
        if (news.value.isNullOrEmpty())
            refreshAllList()
    }

    fun refreshFailedRequest() =
        recipeDataSource.getSource()?.retryFailedQuery()

    fun refreshAllList() =
        recipeDataSource.getSource()?.refresh()

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .setPageSize(5 * 2)
        .build()
}