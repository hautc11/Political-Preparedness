package hautc.study.politicalpreparedness.network.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import hautc.study.politicalpreparedness.util.Utils
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity(tableName = "election_table")
@Parcelize
data class Election(
	@PrimaryKey val id: String,
	val name: String,
	val electionDay: Date,
	val ocdDivisionId: String
) : Parcelable {
	fun getStateAndCountryStr() = Utils.getStateFromOcdDivisionId(ocdDivisionId)
}