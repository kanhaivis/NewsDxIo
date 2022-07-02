package model.section

import kotlinx.serialization.Serializable

@Serializable
data class SectionResponse(
    var DATA: ArrayList<DATA>,
    val STATUS: Boolean,
    val STATUS_MSG: String
)