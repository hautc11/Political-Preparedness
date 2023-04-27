package hautc.study.politicalpreparedness.repository

import android.util.Log
import hautc.study.politicalpreparedness.network.NetworkProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PoliticalPreparednessRepository {

	suspend fun getElections() {
		try {
			withContext(Dispatchers.IO) {
				val result = NetworkProvider.civicsApiService.getElections()
				Log.d("xxx", "getElections: ${result.elections}")
			}
		} catch (e: java.lang.Exception) {
			e.printStackTrace()
		}
	}
}