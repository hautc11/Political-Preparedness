package hautc.study.politicalpreparedness.util

object Utils {
	fun getStateFromOcdDivisionId(ocdDivision: String): String {
		val splitStr = ocdDivision.split("/")
		val country = splitStr[1].substringAfter(":")
		val state = if (splitStr.size > 2) splitStr[2].substringAfter(":") else "ca"
		return "$state, $country"
	}
}