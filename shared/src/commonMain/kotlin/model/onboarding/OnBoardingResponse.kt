package model.onboarding

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnBoardingResponse(
    val STATUS_MSG: String,
    val DATA: List<DATA>,
    val STATUS: Boolean
)

@Serializable
data class DATA(
     val image_url: String,
     val title: String
)