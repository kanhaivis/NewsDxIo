package model.home

import kotlinx.serialization.Serializable

@Serializable
data class ARTICLES(
    var SECTION_NAME: String? ="",
    var SECTION_ID: String?  = "",
    var ARTICLE_ID: String? ="",
    var PROP_ID: String? ="",
    var PROP_KEY: String?  = "",
    var AUDIO_URL: String ="",
    var AUTHOR: String?  = "",
    var AUTHOR_PHOTO: String?  = "",
    var CATEGORY: String?  = "",
    var DESC_PART_1: String?  = "",
    var DESC_PART_2: String?  = "",
    var THUMBNAIL: String?  = "",
    var IMAGES: List<IMAGES>? = listOf(),
    var LEAD_TEXT: String?  = "",
    var LINK: String?  = "",
    var LOCATION: String?  = "",
    var PUBLISH_DATE: String?  = "",
    var PUBLISH_DATE_GMT_MILLIS: Long? =0,
    var RELATED_ARTICLES: List<ARTICLES> = listOf(),
    var SHORT_DESCRIPTION: String?  = "",
    var TITLE: String?  = "",
    var TYPE: String?  = "",
    var VIDEO_URL: String?  = "",
    var guid: String?  = "",
    var premium: Boolean  = false,
    var HAS_IMAGES: Boolean  = false,
    var PREMIUM_MESSAGE: HashMap<String, String> = hashMapOf()
)

@Serializable
data class PREMIUMMESSAGE(var text: String? = "", var sub_line: String? = "", var premium: Boolean  = false)