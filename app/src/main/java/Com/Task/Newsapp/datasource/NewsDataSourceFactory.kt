package Com.Task.newsApp.datasource

import Com.Task.newsApp.Model.Articles
import Com.Task.newsApp.repository.NewsRepository
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope

class NewsDataSourceFactory(
    private val repository: NewsRepository,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Articles>() {

    val source = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, Articles> {
        val source = NewsDataSource(repository, scope)
        this.source.postValue(source)
        return source
    }

    fun getSource() = source.value

}