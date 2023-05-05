package hautc.study.politicalpreparedness.representative

import hautc.study.politicalpreparedness.network.models.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository
import hautc.study.politicalpreparedness.representative.model.Representative
import kotlinx.coroutines.launch

class RepresentativeViewModel(private val politicalPreparednessRepository: PoliticalPreparednessRepository) :
	ViewModel() {

	private var _representatives = MutableLiveData<List<Representative>>()
	val representatives: LiveData<List<Representative>>
		get() = _representatives

	var userAddress: Address = Address()

	var currentSelectedStatePosition: Int = 1

    fun getRepresentativeListByAddress(address: String) {
		viewModelScope.launch {
			val (offices, officials) = politicalPreparednessRepository.getRepresentativesByAddress(address)
			_representatives.value = offices.flatMap { office -> office.getRepresentatives(officials) }
		}
    }
}
