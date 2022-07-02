package model.home

import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    val DATA: DATA,
    val STATUS: Boolean,
    val STATUS_MSG: String
)