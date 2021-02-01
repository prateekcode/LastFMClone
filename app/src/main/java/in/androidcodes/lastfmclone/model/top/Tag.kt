package `in`.androidcodes.lastfmclone.model.top

import com.google.gson.annotations.SerializedName

data class Tag(

    @SerializedName("count")
    val count: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("reach")
    val reach: Int

)