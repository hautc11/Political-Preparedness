package hautc.study.politicalpreparedness.election

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hautc.study.politicalpreparedness.network.models.Election
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository
import kotlinx.coroutines.launch

class ElectionsViewModel(private val politicalPreparednessRepository: PoliticalPreparednessRepository) :
	ViewModel() {

	private var _upComingElections = MutableLiveData<List<Election>>()
	val upComingElections: LiveData<List<Election>>
		get() = _upComingElections

	private var _savedElections = MutableLiveData<List<Election>>()
	val savedElection: LiveData<List<Election>>
		get() = _savedElections

	fun getUpcomingElections() {
		viewModelScope.launch {
			val result = politicalPreparednessRepository.getElections()
			_upComingElections.value = result.elections
		}
	}

	fun getSavedElections() {
		viewModelScope.launch {
			val result = politicalPreparednessRepository.getSavedElections()
			_savedElections.value = result
		}
	}
}