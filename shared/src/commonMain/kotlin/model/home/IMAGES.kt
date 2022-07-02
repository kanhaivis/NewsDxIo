package model.home

import kotlinx.serialization.Serializable

@Serializable
data class IMAGES(
    val CAPTION: String,
    val IMAGE_ID: String
)