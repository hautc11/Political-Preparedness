package hautc.study.politicalprepareness.network.models

import com.google.gson.annotations.SerializedName
import hautc.study.politicalprepareness.representative.model.Representative

data class Office (
	val name: String,
	@SerializedName("divisionId") val division: Division,
	@SerializedName("officialIndices") val officials: List<Int>
) {
    fun getRepresentatives(officials: List<Official>): List<Representative> {
        return this.officials.map { index ->
            Representative(officials[index], this)
        }
    }
}
