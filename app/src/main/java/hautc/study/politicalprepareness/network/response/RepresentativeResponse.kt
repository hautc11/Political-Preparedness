package hautc.study.politicalprepareness.network.response

import hautc.study.politicalprepareness.network.models.Office
import hautc.study.politicalprepareness.network.models.Official

data class RepresentativeResponse(
	val offices: List<Office>,
	val officials: List<Official>
)