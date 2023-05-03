package hautc.study.politicalpreparedness.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hautc.study.politicalprepareness.databinding.FragmentLaunchBinding

class LaunchFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val binding = FragmentLaunchBinding.inflate(inflater)
		binding.lifecycleOwner = this

		binding.btnUpcomingElections.setOnClickListener { navToUpcomingElections() }
		binding.btnFindMyRepresentatives.setOnClickListener { navToRepresentatives() }

		return binding.root
	}

	private fun navToUpcomingElections() {
		this.findNavController().navigate(LaunchFragmentDirections.toElectionsFragment())
	}

	private fun navToRepresentatives() {
		this.findNavController().navigate(LaunchFragmentDirections.toRepresentativeFragment())
	}

}
