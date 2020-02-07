package Com.Task.newsApp.Model

import com.google.gson.annotations.SerializedName

data class News(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<Articles?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)