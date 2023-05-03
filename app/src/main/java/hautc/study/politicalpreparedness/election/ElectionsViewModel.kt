package hautc.study.politicalpreparedness.election

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hautc.study.politicalpreparedness.network.models.Election
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ElectionsViewModel(private val politicalPreparednessRepository: PoliticalPreparednessRepository) :
	ViewModel() {

	private var _upComingElections = MutableLiveData<List<Election>>()
	val upComingElections: LiveData<List<Election>>
		get() = _upComingElections

	//TODO: Create live data val for upcoming elections

	//TODO: Create live data val for saved elections

	//TODO: Create val and functions to populate live data for upcoming elections from the API and saved elections from local database

	//TODO: Create functions to navigate to saved or upcoming election voter info

	fun getUpcomingElections() {
		viewModelScope.launch(Dispatchers.IO) {
			val result = politicalPreparednessRepository.getElections()
			_upComingElections.postValue(result.elections)
		}
	}
}