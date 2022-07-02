package model.home

import kotlinx.serialization.Serializable

@Serializable
data class HTMLWIDGET(
    val ACTION: String ? ="",
    val IS_ENABLED: Boolean = false,
    val POSITION: String? ="",
    val SOURCE: String? ="",
    val URL: String? =""
)