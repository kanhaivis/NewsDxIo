package model.onbording

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val image_url: String,
    val title: String
)