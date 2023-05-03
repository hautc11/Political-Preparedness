package hautc.study.politicalpreparedness.election

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import hautc.study.politicalpreparedness.database.ElectionDatabase
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository
import hautc.study.politicalprepareness.R
import hautc.study.politicalprepareness.databinding.FragmentVoterInfoBinding

class VoterInfoFragment : Fragment() {

	private lateinit var binding: FragmentVoterInfoBinding

	private val args: VoterInfoFragmentArgs by navArgs()

	private val viewModel: VoterInfoViewModel by lazy {
		val database = ElectionDatabase.getInstance(requireContext())
		val repository = PoliticalPreparednessRepository(database)
		ViewModelProvider(this, VoterInfoViewModelFactory(repository))[VoterInfoViewModel::class.java]
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentVoterInfoBinding.inflate(inflater)

		viewModel.getVoterInfo(args.electionId, args.stateAndCountry)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initObserve()
	}

	private fun initObserve() {
		viewModel.voterInfo.observe(viewLifecycleOwner) { voteInfo ->
			if (voteInfo?.election == null) {
				binding.groupView.isVisible = false
				binding.tvError.isVisible = true
				binding.btnFollowElection.run {
					text = getString(R.string.back)
					setOnClickListener {
						findNavController().popBackStack()
					}
				}
			} else {
				binding.groupView.isVisible = true
				binding.electionDate.text = voteInfo.election.electionDay.toString()
				binding.electionName.title = voteInfo.election.name
				binding.stateLocations.setOnClickListener {
					voteInfo.state?.firstOrNull()?.electionAdministrationBody?.votingLocationFinderUrl?.let {
						openBrowser(it)
					}
				}
				binding.stateBallot.setOnClickListener {
					voteInfo.state?.firstOrNull()?.electionAdministrationBody?.ballotInfoUrl?.let {
						openBrowser(it)
					}
				}
				binding.btnFollowElection.run {
					text = getString(R.string.follow_election)
					setOnClickListener {
						//TODO
					}
				}
			}
		}
	}

	private fun openBrowser(url: String) {
		val intent = Intent(Intent.ACTION_VIEW)
		intent.data = Uri.parse(url)
		startActivity(intent)
	}
}