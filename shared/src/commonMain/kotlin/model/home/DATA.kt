package model.home

import kotlinx.serialization.Serializable

@Serializable
data class DATA(
    var ARTICLES: List<ARTICLES>?,
    var BANNER: List<ARTICLES>?,
    var HTML_WIDGET: HTMLWIDGET?,
    var LIVE_WIDGET: LIVEWIDGET?,
    var WIDGETS: List<WIDGETS>?
)