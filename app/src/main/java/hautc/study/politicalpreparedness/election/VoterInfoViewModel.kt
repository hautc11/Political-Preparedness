package hautc.study.politicalpreparedness.election

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hautc.study.politicalpreparedness.database.ElectionDao
import hautc.study.politicalpreparedness.network.models.Election
import hautc.study.politicalpreparedness.network.response.VoterInfoResponse
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VoterInfoViewModel(
	private val politicalPreparednessRepository: PoliticalPreparednessRepository
) : ViewModel() {

	private var _voterInfo = MutableLiveData<VoterInfoResponse>()
	val voterInfo: LiveData<VoterInfoResponse>
		get() = _voterInfo

	//TODO: Add live data to hold voter info

	//TODO: Add var and methods to populate voter info

	//TODO: Add var and methods to support loading URLs

	//TODO: Add var and methods to save and remove elections to local database
	//TODO: cont'd -- Populate initial state of save button to reflect proper action based on election saved status

	fun getVoterInfo(electionId: String, address: String) {
		viewModelScope.launch(Dispatchers.IO) {
			val result = politicalPreparednessRepository.getVoterInfo(electionId, address)
			_voterInfo.postValue(result)
		}
	}

	/**
	 * Hint: The saved state can be accomplished in multiple ways. It is directly related to how elections are saved/removed from the database.
	 */

}