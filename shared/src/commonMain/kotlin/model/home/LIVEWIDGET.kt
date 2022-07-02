package model.home

import kotlinx.serialization.Serializable

@Serializable
data class LIVEWIDGET(
    val ARTICLES: List<ARTICLES>,
    val ARTICLES_LIMIT: Int,
    val IS_ENABLED: Boolean,
    val POSITION: String,
    val REFRESH_INTERVAL: String
)