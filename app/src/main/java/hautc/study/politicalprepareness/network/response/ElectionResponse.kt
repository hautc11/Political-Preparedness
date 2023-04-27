package hautc.study.politicalprepareness.network.response

import hautc.study.politicalprepareness.network.models.Election

data class ElectionResponse(
        val kind: String,
        val elections: List<Election>
)