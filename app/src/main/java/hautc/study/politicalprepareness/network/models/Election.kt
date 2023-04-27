package hautc.study.politicalprepareness.network.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Election(
	val name: String,
	val electionDay: Date,
	@SerializedName("ocdDivisionId")
	val division: Division
)