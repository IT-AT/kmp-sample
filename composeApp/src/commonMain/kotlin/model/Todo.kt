package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("title")
    val title: String? = null
)