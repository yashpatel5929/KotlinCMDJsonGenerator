package remote.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ImageUrls(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("err") var err: String? = null,
    @SerializedName("images") var images: JsonObject? = JsonObject()
)
