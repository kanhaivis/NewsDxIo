package model.onbording

import kotlinx.serialization.Serializable
import model.onbording.Data

@Serializable
data class OnBoardingResponse(
    val STATUS_MSG: String,
    val data: List<Data>,
    val status: Boolean
)