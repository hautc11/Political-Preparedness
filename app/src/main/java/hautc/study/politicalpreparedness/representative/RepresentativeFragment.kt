package hautc.study.politicalpreparedness.representative

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import hautc.study.politicalpreparedness.database.ElectionDatabase
import hautc.study.politicalpreparedness.network.models.Address
import hautc.study.politicalpreparedness.repository.PoliticalPreparednessRepository
import hautc.study.politicalpreparedness.representative.adapter.RepresentativeListAdapter
import hautc.study.politicalpreparedness.representative.adapter.setNewValue
import hautc.study.politicalprepareness.BuildConfig
import hautc.study.politicalprepareness.R
import hautc.study.politicalprepareness.databinding.FragmentRepresentativeBinding
import java.util.Locale


class RepresentativeFragment : Fragment() {

	private lateinit var binding: FragmentRepresentativeBinding

	private val mAdapter by lazy { RepresentativeListAdapter() }

	private val viewModel: RepresentativeViewModel by lazy {
		val database = ElectionDatabase.getInstance(requireContext())
		val repository = PoliticalPreparednessRepository(database)
		ViewModelProvider(
			this,
			RepresentativeViewModelFactory(repository)
		)[RepresentativeViewModel::class.java]
	}

	private val permissionLauncher = registerForActivityResult(
		ActivityResultContracts.RequestMultiplePermissions()
	) { permissions ->
		val granted = permissions.entries.all { it.value }
		if (granted) {
			handleUserAddress()
		} else {
			Snackbar.make(
				binding.root,
				R.string.permission_denied, Snackbar.LENGTH_INDEFINITE
			)
				.setAction(R.string.settings) {
					startActivity(Intent().apply {
						action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
						data = Uri.fromParts(
							"package",
							BuildConfig.APPLICATION_ID,
							"RepresentativesFragment"
						)
						flags = Intent.FLAG_ACTIVITY_NEW_TASK
					})
				}.show()
		}
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
			viewModel.getRepresentativeListByAddress(address = getAddressStr())
			hideKeyboard()
		}

		binding.buttonLocation.setOnClickListener {
			if (isFineLocationAndAccessCoarseLocationGranted()) {
				handleUserAddress()
			} else {
				requestFineLocationAndAccessCoarseLocationPermission()
			}
		}
	}

	@SuppressLint("MissingPermission")
	private fun handleUserAddress() {
		val fusedLocationClient: FusedLocationProviderClient =
			LocationServices.getFusedLocationProviderClient(requireActivity())

		fusedLocationClient.lastLocation.addOnSuccessListener {
			viewModel.userAddress.value = geoCodeLocation(it)
			binding.state.setNewValue(viewModel.userAddress.value?.state)
			binding.loadingIndicator.isVisible = true
			viewModel.getRepresentativeListByAddress(address = getAddressStr())
		}

	}

	private fun requestFineLocationAndAccessCoarseLocationPermission() {
		val mPermissionArray = arrayOf(
			Manifest.permission.ACCESS_FINE_LOCATION,
			Manifest.permission.ACCESS_COARSE_LOCATION
		)
		permissionLauncher.launch(mPermissionArray)
	}

	private fun isFineLocationAndAccessCoarseLocationGranted(): Boolean {
		val isFineLocationGranted = ContextCompat.checkSelfPermission(
			requireContext(),
			Manifest.permission.ACCESS_FINE_LOCATION
		) == PackageManager.PERMISSION_GRANTED
		val isAccessCoarseLocationGranted = ContextCompat.checkSelfPermission(
			requireContext(),
			Manifest.permission.ACCESS_COARSE_LOCATION
		) == PackageManager.PERMISSION_GRANTED
		return isFineLocationGranted && isAccessCoarseLocationGranted
	}

	private fun initObserve() {
		viewModel.representatives.observe(viewLifecycleOwner) { representatives ->
			if (representatives.isNullOrEmpty().not()) {
				binding.loadingIndicator.isVisible = false
				mAdapter.submitList(representatives)
			} else {
				binding.ivErrorConnection.isVisible = true
				binding.loadingIndicator.isVisible = false
			}
		}
	}

	private fun geoCodeLocation(location: Location): Address {
		val geocoder = Geocoder(requireContext(), Locale.getDefault())
		return geocoder.getFromLocation(location.latitude, location.longitude, 1)?.map { address ->
			Address(
				line1 = address.thoroughfare,
				line2 = address.subThoroughfare,
				city = address.locality,
				state = address.adminArea,
				zip = address.postalCode
			)
		}?.first() ?: Address()
	}

	private fun hideKeyboard() {
		val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(requireView().windowToken, 0)
	}

	private fun getAddressStr(): String {
		return viewModel.userAddress.value?.copy(
			state = binding.state.selectedItem.toString()
		)?.toFormattedString() ?: binding.state.selectedItem.toString()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		binding.unbind()
	}

}