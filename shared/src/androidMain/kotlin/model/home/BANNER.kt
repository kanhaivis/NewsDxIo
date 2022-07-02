package model.home

data class BANNER(
    val ARTICLE_ID: String,
    val AUDIO_URL: String,
    val AUTHOR: String,
    val AUTHOR_PHOTO: String,
    val CATEGORY: String,
    val DESC_PART_1: String,
    val IMAGES: List<IMAGES>,
    val LEAD_TEXT: String,
    val LINK: String,
    val LOCATION: String,
    val PROP_KEY: String,
    val PUBLISH_DATE: String,
    val PUBLISH_DATE_GMT_MILLIS: Long,
    val RELATED_ARTICLES: List<Any>,
    val SECTION_ID: String,
    val SECTION_NAME: String,
    val SHORT_DESCRIPTION: String,
    val TITLE: String,
    val TYPE: String,
    val VIDEO_URL: String,
    val guid: String,
    val premium: Boolean
)