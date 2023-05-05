package hautc.study.politicalpreparedness.util

import android.content.Context
import hautc.study.politicalprepareness.R

object Utils {
	fun getStateFromOcdDivisionId(ocdDivision: String): String {
		val splitStr = ocdDivision.split("/")
		val country = splitStr[1].substringAfter(":")
		val state = if (splitStr.size > 2) splitStr[2].substringAfter(":") else "ca"
		return "$state, $country"
	}

	fun getStateFromSpinnerSelectedPosition(context: Context, position: Int): String {
		val data = context.resources.getStringArray(R.array.states)
		return data[position]
	}
}