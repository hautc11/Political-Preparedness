package hautc.study.politicalpreparedness.election

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hautc.study.politicalpreparedness.network.models.Election
import hautc.study.politicalpreparedness.network.response.VoterInfoResponse
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository
import kotlinx.coroutines.launch

class VoterInfoViewModel(
	private val politicalPreparednessRepository: PoliticalPreparednessRepository
) : ViewModel() {

	private var _voterInfo = MutableLiveData<VoterInfoResponse>()
	val voterInfo: LiveData<VoterInfoResponse>
		get() = _voterInfo

	private var _isFollowedByUser = MutableLiveData<Boolean>()
	val isFollowedByUser: LiveData<Boolean>
		get() = _isFollowedByUser

	fun getVoterInfo(electionId: String, address: String) {
		viewModelScope.launch {
			val result = politicalPreparednessRepository.getVoterInfo(electionId, address)
			_voterInfo.value = result
			isCurrentElectionFollowedByUser()
		}
	}

	fun followElection(election: Election) {
		viewModelScope.launch {
			val result = politicalPreparednessRepository.followElection(election)
			if (result.isSuccess) {
				_isFollowedByUser.value = true
			}
		}
	}

	fun unFollowElection(electionId: String) {
		viewModelScope.launch {
			val result = politicalPreparednessRepository.unFollowElection(electionId)
			if (result.isSuccess) {
				_isFollowedByUser.value = false
			}
		}
	}

	private suspend fun isCurrentElectionFollowedByUser() {
		val electionsInLocalDB = politicalPreparednessRepository.getSavedElections()
		voterInfo.value?.election?.let { currentElection ->
			_isFollowedByUser.value = electionsInLocalDB.any { it.id == currentElection.id }
		}
	}

}