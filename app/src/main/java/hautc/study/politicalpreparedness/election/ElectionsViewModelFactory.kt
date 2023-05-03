package hautc.study.politicalpreparedness.election

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository

class ElectionsViewModelFactory(private val politicalPreparednessRepository: PoliticalPreparednessRepository) :
	ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(ElectionsViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return ElectionsViewModel(politicalPreparednessRepository) as T
		}
		throw IllegalArgumentException("Unable to construct viewModel")
	}

}