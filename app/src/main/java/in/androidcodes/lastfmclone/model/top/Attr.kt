package `in`.androidcodes.lastfmclone.model.top

import com.google.gson.annotations.SerializedName

data class Attr(
    @SerializedName("num_res")
    val num_res: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int
)