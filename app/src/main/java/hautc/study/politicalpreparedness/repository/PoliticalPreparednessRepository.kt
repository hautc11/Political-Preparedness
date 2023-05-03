package hautc.study.politicalpreparedness.repository

import hautc.study.politicalpreparedness.database.ElectionDatabase
import hautc.study.politicalpreparedness.network.NetworkProvider
import hautc.study.politicalpreparedness.network.models.Election
import hautc.study.politicalpreparedness.network.response.ElectionResponse
import hautc.study.politicalpreparedness.network.response.VoterInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PoliticalPreparednessRepository(
	private val database: ElectionDatabase
) {

	suspend fun getElections(): ElectionResponse {
		return try {
			withContext(Dispatchers.IO) {
				NetworkProvider.civicsApiService.getElections()
			}
		} catch (e: java.lang.Exception) {
			e.printStackTrace()
			return ElectionResponse()
		}
	}

	suspend fun getVoterInfo(electionId: String, address: String): VoterInfoResponse {
		return try {
			withContext(Dispatchers.IO) {
				NetworkProvider.civicsApiService.getVoterInfo(id = electionId, address = address)
			}
		} catch (e: java.lang.Exception) {
			e.printStackTrace()
			return VoterInfoResponse()
		}
	}

	suspend fun getSavedElections(): List<Election>{
		return database.electionDao.getElection()
	}
}