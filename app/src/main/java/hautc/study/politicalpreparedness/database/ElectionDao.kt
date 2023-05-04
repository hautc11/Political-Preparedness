package hautc.study.politicalpreparedness.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hautc.study.politicalpreparedness.network.models.Election

@Dao
interface ElectionDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertElection(election: Election)

	@Query("SELECT * FROM election_table")
	suspend fun getAllElection(): List<Election>

	@Query("SELECT * FROM election_table where id = :id")
	suspend fun getElectionById(id: String): Election?

	@Query("DELETE FROM election_table WHERE id = :id")
	suspend fun deleteElectionById(id: String)

	@Query("DELETE FROM election_table")
	suspend fun deleteAllElections()

}