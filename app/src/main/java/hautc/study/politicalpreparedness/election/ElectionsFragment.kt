package hautc.study.politicalpreparedness.election

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import hautc.study.politicalpreparedness.database.ElectionDatabase
import hautc.study.politicalpreparedness.election.adapter.ElectionListAdapter
import hautc.study.politicalpreparedness.network.models.Election
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository
import hautc.study.politicalprepareness.databinding.FragmentElectionBinding

class ElectionsFragment: Fragment() {

    private lateinit var binding: FragmentElectionBinding

    private val viewModel: ElectionsViewModel by lazy {
        val database = ElectionDatabase.getInstance(requireContext())
        val repository = PoliticalPreparednessRepository(database)
        ViewModelProvider(this, ElectionsViewModelFactory(repository))[ElectionsViewModel::class.java]
    }

    private val upcomingElectionAdapter by lazy {
        ElectionListAdapter().apply {
            onElectionItemClicked = {
                navigateToElectionDetailFragment(it.id, it.getStateAndCountryStr())
            }
        }
    }

    private fun navigateToElectionDetailFragment(electionId: String, address: String) {
        findNavController().navigate(ElectionsFragmentDirections.toVoterInfoFragment(electionId, address))
    }

//    private val savedElectionAdapter by lazy {
//        ElectionListAdapter().apply {
//            onElectionItemClicked = {
//                Log.d("xxx", "selected: ${it.name}")
//            }
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentElectionBinding.inflate(inflater)
        binding.rvUpcomingElections.adapter = upcomingElectionAdapter
        viewModel.getUpcomingElections()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    private fun initObserve() {
        viewModel.upComingElections.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty().not()) {
                upcomingElectionAdapter.submitList(it)
                binding.loadingIndicator.isVisible = false
            }
        }
    }
}