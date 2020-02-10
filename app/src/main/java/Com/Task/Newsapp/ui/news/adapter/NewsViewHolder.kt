package Com.Task.newsApp.ui.news.adapter

import Com.Task.newsApp.utils.ImageUtils
import Com.Task.newsApp.data.Model.Article
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.news_item.view.*

class NewsViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(
        article: Article?,
        listener: NewsAdapter.OnClickListener
    ) {
        article?.let {
            setupViews(it, itemView)
            setListeners(listener, article)
        }
    }

    private fun setListeners(
        listener: NewsAdapter.OnClickListener,
        article: Article
    ) {
        itemView.setOnClickListener {
            listener.onRowClicked(article)
        }
    }

    private fun setupViews(it: Article, itemView: View) {
        it.urlToImage?.let { it1 ->
            ImageUtils.loadCircleImage(
                it1,
                itemView.newsItemImg,
                itemView.context
            )
        }

        it.title?.let { title -> itemView.newsItemTitle.text = title}
        it.author?.let { author -> itemView.newsItemAuthor.text = author}
        it.publishedAt?.let { date -> itemView.newsItemDate.text = date.substringBefore("T")}

    }
}
