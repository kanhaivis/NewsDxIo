package model.profile

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    var created_at: String? = "",
    var displayName: String? = "",
    var email: String? = "",
    var id: String? = "",
    var phoneNumber: String? = "",
    var photoUrl: String? = ""
)