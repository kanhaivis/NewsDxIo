package model.home

import kotlinx.serialization.Serializable

@Serializable
data class BANNER(
    var ARTICLE_ID: String? ="",
    var AUDIO_URL: String? ="",
    var AUTHOR: String? ="",
    var AUTHOR_PHOTO: String? ="",
    var CATEGORY: String? ="",
    var DESC_PART_1: String? ="",
    var IMAGES: List<IMAGES>?,
    var LEAD_TEXT: String? ="",
    var LINK: String? ="",
    var LOCATION: String? ="",
    var PROP_KEY: String? ="",
    var PUBLISH_DATE: String? ="",
    var PUBLISH_DATE_GMT_MILLIS: Long? =0,
    var RELATED_ARTICLES: List<ARTICLES>?,
    var SECTION_ID: String? ="",
    var SECTION_NAME: String? ="",
    var SHORT_DESCRIPTION: String? ="",
    var TITLE: String? ="",
    var TYPE: String? ="",
    var VIDEO_URL: String? ="",
    var guid: String? ="",
    var premium: Boolean =  false
)