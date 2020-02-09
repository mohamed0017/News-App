package Com.Task.newsApp.ui.news.adapter

import Com.Task.newsApp.api.NetworkState
import Com.Task.newsApp.gone
import Com.Task.newsApp.visible
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_repo_state.view.*

class RepoStateViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(networkState: NetworkState?, callback: NewsAdapter.OnClickListener) {
        setVisibleRightViews(networkState)
        itemView.repo_state_button.setOnClickListener { callback.onRetryClick() }
    }

    private fun setVisibleRightViews(networkState: NetworkState?) {
        when (networkState) {
            NetworkState.FAILED -> {
                itemView.repo_state_button.visible()
                itemView.repo_error_msg.visible()
                itemView.repo_progress_bar.gone()
            }
            NetworkState.RUNNING -> {
                itemView.repo_state_button.gone()
                itemView.repo_error_msg.gone()
                itemView.repo_progress_bar.visible()
            }
            NetworkState.SUCCESS -> {
                itemView.repo_state_button.gone()
                itemView.repo_error_msg.gone()
                itemView.repo_progress_bar.gone()
            }
        }
    }
}