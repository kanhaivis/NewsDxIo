package model.home

data class LIVEWIDGET(
    val ARTICLES: List<Any>,
    val ARTICLES_LIMIT: Int,
    val IS_ENABLED: Boolean,
    val POSITION: String,
    val REFRESH_INTERVAL: String
)