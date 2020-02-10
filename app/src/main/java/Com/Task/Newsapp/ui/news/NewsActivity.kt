package Com.Task.newsApp.ui.news

import Com.Task.newsApp.R
import Com.Task.newsApp.data.Model.Article
import Com.Task.newsApp.data.api.NetworkState
import Com.Task.newsApp.ui.news.adapter.NewsAdapter
import Com.Task.newsApp.ui.newsDetails.NewsDetailsActivity
import Com.Task.newsApp.utils.gone
import Com.Task.newsApp.utils.visible
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val News_Details = "news_details"

class NewsActivity : AppCompatActivity(), NewsAdapter.OnClickListener {

    private val repositoryRecyclerViewAdapter = NewsAdapter(this)
    private val viewModel by viewModel<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        loadNews()
        setupRecyclerView()
        setupListeners()
        observeViewModelData()
    }

    private fun loadNews() {
        viewModel.loadInitialNews()
    }

    private fun setupListeners() {
        newsRetryBut.setOnClickListener { viewModel.refreshAllList() }
    }

    private fun observeViewModelData() {
        viewModel.networkState?.observe(
            this,
            Observer { repositoryRecyclerViewAdapter.updateNetworkState(it) })
        viewModel.news.observe(this, Observer { repositoryRecyclerViewAdapter.submitList(it) })
    }

    private fun setupRecyclerView() {
        newsList.adapter = repositoryRecyclerViewAdapter
    }

    //Override onRetryClick from NewsAdapter.onNewsRowClicked
    override fun onRowClicked(article: Article) {
        NewsDetailsActivity.openNewsDetails(this, article)
    }

    //Override onRetryClick from NewsAdapter.OnClickListener
    override fun onRetryClick() {
        viewModel.refreshFailedRequest()
    }

    //Override whenListIsUpdated from NewsAdapter.OnClickListener
    override fun whenListIsUpdated(size: Int, networkState: NetworkState?) {
        setInitialStates()
        if (size == 0) {
            setSizeZeroInitialState()
            when (networkState) {
                NetworkState.SUCCESS -> {
                    newsTextNetwork.text = getString(R.string.news_empty)
                    newsTextNetwork.visible()
                    newsRetryBut.gone()
                }
                NetworkState.FAILED -> {
                    newsTextNetwork.text = getString(R.string.error_msg)
                    newsImageWarning.visible()
                    newsTextNetwork.visible()
                    newsRetryBut.visible()
                }
                NetworkState.RUNNING -> {
                    newsProgressBar.visible()
                    newsRetryBut.gone()
                    newsTextNetwork.gone()
                }
            }
        }
    }

    private fun setSizeZeroInitialState() {
        newsTextNetwork.text = getString(R.string.news_empty)
        newsTextNetwork.visible()
    }

    private fun setInitialStates() {
        newsImageWarning.gone()
        newsTextNetwork.gone()
        newsProgressBar.gone()
        newsRetryBut.gone()
    }
}
