package hautc.study.politicalpreparedness.network.response

import hautc.study.politicalpreparedness.network.models.Election
import hautc.study.politicalpreparedness.network.models.ElectionOfficial
import hautc.study.politicalpreparedness.network.models.State

class VoterInfoResponse (
	val election: Election? = null,
	val pollingLocations: String? = null,
	val contests: String? = null,
	val state: List<State>? = null,
	val electionElectionOfficials: List<ElectionOfficial>? = null
)