package model.profile

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    val STATUS_MSG: String? = "",
    val data: Data,
    val status: Boolean
)