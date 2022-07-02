package model.article

import kotlinx.serialization.Serializable
import model.home.ARTICLES

@Serializable
data class ArticleByIdResponse(
    val DATA: DATAArticle,
    val STATUS: Boolean,
    val STATUS_MSG: String
)

@Serializable
data class DATAArticle(
    val ARTICLES: ArrayList<ARTICLES>,
    val CURRENTPAGE: String,
    val TIMESTAMP: String
)