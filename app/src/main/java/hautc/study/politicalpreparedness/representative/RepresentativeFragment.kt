package hautc.study.politicalpreparedness.representative

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hautc.study.politicalpreparedness.database.ElectionDatabase
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository
import hautc.study.politicalpreparedness.representative.adapter.RepresentativeListAdapter
import hautc.study.politicalpreparedness.util.Utils
import hautc.study.politicalprepareness.databinding.FragmentRepresentativeBinding

class RepresentativeFragment : Fragment() {

	private lateinit var binding: FragmentRepresentativeBinding

	private val mAdapter by lazy { RepresentativeListAdapter() }

	private val viewModel: RepresentativeViewModel by lazy {
		val database = ElectionDatabase.getInstance(requireContext())
		val repository = PoliticalPreparednessRepository(database)
		ViewModelProvider(this, RepresentativeViewModelFactory(repository))[RepresentativeViewModel::class.java]
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentRepresentativeBinding.inflate(inflater)
		binding.viewModel = viewModel
		binding.lifecycleOwner = this
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.rvRepresentative.adapter = mAdapter
		initObserve()
		binding.buttonSearch.setOnClickListener {
			binding.loadingIndicator.isVisible = true

			val formattedUserAddress = viewModel.userAddress.copy(
				state = Utils.getStateFromSpinnerSelectedPosition( requireContext(), viewModel.currentSelectedStatePosition)
			).toFormattedString()

			viewModel.getRepresentativeListByAddress(address = formattedUserAddress)

			hideKeyboard()
		}
	}

	private fun initObserve() {
		viewModel.representatives.observe(viewLifecycleOwner) { representatives ->
			if (representatives.isNullOrEmpty().not()) {
				binding.loadingIndicator.isVisible = false
				mAdapter.submitList(representatives)
			} else {
				binding.ivErrorConnection.isVisible = true
			}
		}
	}

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        //TODO: Handle location permission result to get location on permission granted
//    }
//
//    private fun checkLocationPermissions(): Boolean {
//        return if (isPermissionGranted()) {
//            true
//        } else {
//            //TODO: Request Location permissions
//            false
//        }
//    }
//
//    private fun isPermissionGranted() : Boolean {
//        //TODO: Check if permission is already granted and return (true = granted, false = denied/other)
//		return true
//    }
//
//    private fun getLocation() {
//        //TODO: Get location from LocationServices
//        //TODO: The geoCodeLocation method is a helper function to change the lat/long location to a human readable street address
//    }

//    private fun geoCodeLocation(location: Location): Address {
//        val geocoder = Geocoder(context, Locale.getDefault())
//        return geocoder.getFromLocation(location.latitude, location.longitude, 1)
//                .map { address ->
//                    Address(address.thoroughfare, address.subThoroughfare, address.locality, address.adminArea, address.postalCode)
//                }
//                .first()
//    }

	private fun hideKeyboard() {
		val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(requireView().windowToken, 0)
	}

	companion object {
		//TODO: Add Constant for Location request
	}
}