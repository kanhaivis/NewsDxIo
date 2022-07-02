package model.section

import kotlinx.serialization.Serializable

@Serializable
data class WIDGET(
    var ACTION: String? ="",
    var IS_ENABLED: Boolean = false,
    var POSITION: String? ="",
    var SOURCE: String? ="",
    var URL: String? =""
)