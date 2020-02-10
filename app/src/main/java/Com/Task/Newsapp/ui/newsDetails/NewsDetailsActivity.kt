package Com.Task.newsApp.ui.newsDetails

import Com.Task.newsApp.R
import Com.Task.newsApp.data.Model.Article
import Com.Task.newsApp.ui.news.News_Details
import Com.Task.newsApp.utils.ImageUtils
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {

    companion object {
        fun openNewsDetails(context: Context, article: Article) {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(News_Details, article)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val newsDetails = intent.getParcelableExtra<Article>(News_Details)
        setNewsDetails(newsDetails)
    }

    private fun setNewsDetails(newsDetails: Article?) {
        if (newsDetails != null) {
            newsTitle.text = newsDetails.title
            newsAuthor.text = newsDetails.author
            newsDate.text = newsDetails.publishedAt?.substringBefore("T")
            newsDesc.text = newsDetails.description
            newsContent.text = newsDetails.content

            newsDetails.urlToImage?.let { ImageUtils.loadImage(it, newsImg, this) }
        }
    }
}
