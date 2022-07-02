package model.home

import kotlinx.serialization.Serializable

@Serializable
data class WIDGETS(
    val ARTICLES: List<ARTICLES>,
    val DISPLAY_ORDER: Int,
    val SECTION_ID: String,
    val SECTION_NAME: String,
    val TYPE: String
)