package Com.Task.newsApp.ui.news.adapter

import Com.Task.newsApp.ImageUtils
import Com.Task.newsApp.Model.Articles
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.news_item.view.*

class NewsViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(
        article: Articles?,
        listener: NewsAdapter.OnClickListener
    ) {
        article?.let {
            setupViews(it, itemView)
            setListeners(listener, article)
        }
    }

    private fun setListeners(
        listener: NewsAdapter.OnClickListener,
        article: Articles
    ) {
        itemView.setOnClickListener {
            listener.onRowClicked(article)
        }
    }

    private fun setupViews(it: Articles, itemView: View) {
        it.urlToImage?.let { it1 ->
            ImageUtils.loadImage(
                it1,
                itemView.newsItemImg,
                itemView.context
            )
        }

        it.title?.let { title -> itemView.newsItemTitle.text = title}
        it.author?.let { author -> itemView.newsItemAuthor.text = author}
        it.publishedAt?.let { date -> itemView.newsItemDate.text = date}

    }
}
