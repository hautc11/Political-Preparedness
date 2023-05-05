package hautc.study.politicalpreparedness.network.response

import hautc.study.politicalpreparedness.network.models.Office
import hautc.study.politicalpreparedness.network.models.Official

data class RepresentativeResponse(
	val offices: List<Office> = listOf(),
	val officials: List<Official> = listOf()
)