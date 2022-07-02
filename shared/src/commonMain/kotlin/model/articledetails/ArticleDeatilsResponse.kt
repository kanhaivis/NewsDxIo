package model.articledetails

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDeatilsResponse(
    val DATA: DATA,
    val STATUS: Boolean,
    val STATUS_MSG: String
)

@Serializable
data class DATA(
    val ARTICLE_ID: String,
    val DESCRIPTION: String,
    val IMAGES: List<IMAGES>,
    val LEAD_TEXT: String,
    val LINK: String,
    val LOCATION: String,
    val PUBLISH_DATE: String,
    val SECTION_NAME: String,
    val TIMESTAMP: String,
    val TITLE: String,
    val guid: String,
    val premium: Boolean
)

@Serializable
data class IMAGES(
    val ARTICLE_ID: String,
    val CAPTION: String,
    val ID: String,
    val IMAGE_ID: String,
    val INSERTED_ON: String,
    val PROP_ID: String
)