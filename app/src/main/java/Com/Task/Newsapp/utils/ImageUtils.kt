package Com.Task.newsApp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class ImageUtils {
    companion object {
        fun loadCircleImage(url: String, imageView: ImageView, context: Context) {
            Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.circleCropTransform())
                .into(imageView)
        }

        fun loadImage(url: String, imageView: ImageView, context: Context) {
            Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }
}