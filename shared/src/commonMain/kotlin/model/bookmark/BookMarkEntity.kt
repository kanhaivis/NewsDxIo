package model.bookmark

import kotlinx.serialization.Serializable

@Serializable
data class BookMarkEntity(
    var id: Int,
    var articleID: String
)
