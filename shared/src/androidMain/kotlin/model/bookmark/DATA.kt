package model.bookmark

data class DATA(
    val DESCRIPTION: String,
    val IMAGES: List<IMAGES>,
    val LEAD_TEXT: String,
    val LINK: String,
    val LOCATION: String,
    val PUBLISH_DATE: String,
    val TIMESTAMP: String,
    val TITLE: String,
    val guid: String,
    val premium: Boolean
)