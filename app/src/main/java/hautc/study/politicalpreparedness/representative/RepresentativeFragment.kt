package hautc.study.politicalpreparedness.representative

import androidx.fragment.app.Fragment

class RepresentativeFragment : Fragment() {

//    companion object {
//        //TODO: Add Constant for Location request
//    }
//
//    //TODO: Declare ViewModel
//
//    override fun onCreateView(inflater: LayoutInflater,
//                              container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//
//        //TODO: Establish bindings
//
//        //TODO: Define and assign Representative adapter
//
//        //TODO: Populate Representative adapter
//
//        //TODO: Establish button listeners for field and location search
//        return View.inflate(context, R.layout.fragment_election, container)
//
//    }
//
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
//    }
//
//    private fun getLocation() {
//        //TODO: Get location from LocationServices
//        //TODO: The geoCodeLocation method is a helper function to change the lat/long location to a human readable street address
//    }
//
//    private fun geoCodeLocation(location: Location): Address {
//        val geocoder = Geocoder(context, Locale.getDefault())
//        return geocoder.getFromLocation(location.latitude, location.longitude, 1)
//                .map { address ->
//                    Address(address.thoroughfare, address.subThoroughfare, address.locality, address.adminArea, address.postalCode)
//                }
//                .first()
//    }
//
//    private fun hideKeyboard() {
//        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
//    }

}