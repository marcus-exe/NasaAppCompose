package android.com.nasaapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NasaInfo(
    val name : String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val imgSrc : String
)
