package hautc.study.politicalprepareness.network.response

import hautc.study.politicalprepareness.network.models.Election
import hautc.study.politicalprepareness.network.models.ElectionOfficial
import hautc.study.politicalprepareness.network.models.State

class VoterInfoResponse (
	val election: Election,
	val pollingLocations: String? = null,
	val contests: String? = null,
	val state: List<State>? = null,
	val electionElectionOfficials: List<ElectionOfficial>? = null
)