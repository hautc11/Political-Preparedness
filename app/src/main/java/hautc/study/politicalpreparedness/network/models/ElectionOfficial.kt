package hautc.study.politicalpreparedness.network.models

import com.google.gson.annotations.SerializedName

data class ElectionOfficial(
    val name: String,
    val title: String,
    @SerializedName("officePhoneNumber") val phone: String,
    @SerializedName("faxNumber") val fax: String,
    val emailAddress: String
)