package hautc.study.politicalpreparedness.representative

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository

class RepresentativeViewModelFactory(private val politicalPreparednessRepository: PoliticalPreparednessRepository) :
	ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(RepresentativeViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return RepresentativeViewModel(politicalPreparednessRepository) as T
		}
		throw IllegalArgumentException("Unable to construct viewModel")
	}

}