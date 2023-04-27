package hautc.study.politicalprepareness.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import hautc.study.politicalprepareness.network.models.Division
import java.util.Date


@Entity(tableName = "election_table")
data class ElectionEntity(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "name")val name: String,
        @ColumnInfo(name = "electionDay")val electionDay: Date,
        @Embedded(prefix = "division_") @SerializedName("ocdDivisionId") val division: Division
)