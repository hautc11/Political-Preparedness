package hautc.study.politicalpreparedness.election

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hautc.study.politicalpreparedness.database.ElectionDao
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository

class VoterInfoViewModelFactory(
	private val politicalPreparednessRepository: PoliticalPreparednessRepository
) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(VoterInfoViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return VoterInfoViewModel(politicalPreparednessRepository) as T
		}
		throw IllegalArgumentException("Unable to construct viewModel")
	}
}