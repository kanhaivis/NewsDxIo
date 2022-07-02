package model.section

import kotlinx.serialization.Serializable

@Serializable
data class DATA(
    var ID: String?  = "",
    var PROP_ID: String? = "",
    var PARENT_ID: String? = "",
    var SECTION_NAME: String? = "",
    var SECTION_TYPE: String? = "",
    var SECTION_FEED: String? = "",
    var SECTION_DESC: String? = "",
    var DISPLAY_ORDER: String? = "",
    var SECTION_THUMBNAIL: String? = "",
    var SHOW_IN_HAMBURGER: String? = "",
    var SHOW_IN_TOP_MENU: String? = "",
    var SHOW_IN_FEEDS: String? = "",
    var IS_CUSTOMIZABLE: String? = "",
    var CRON_TIME_IN_MINS: String? = "",
    var TIMESTAMP: String? = "",
    var STATUS: String? = "",
    var SUBSECTIONS: List<DATA> = listOf(),
    var WIDGET : WIDGET = WIDGET()
){

}

