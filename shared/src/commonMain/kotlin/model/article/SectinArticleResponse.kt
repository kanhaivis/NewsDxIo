package model.article

import kotlinx.serialization.Serializable
import model.home.ARTICLES

@Serializable
data class SectinArticleResponse(
    val DATA: DATA,
    val STATUS: Boolean,
    val STATUS_MSG: String
)

@Serializable
data class DATA(
    val ARTICLES: List<ARTICLES>,
    val CURRENTPAGE: Int,
    val TIMESTAMP: String
)

