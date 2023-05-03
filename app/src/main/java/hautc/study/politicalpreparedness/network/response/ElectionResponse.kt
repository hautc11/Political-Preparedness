package hautc.study.politicalpreparedness.network.response

import hautc.study.politicalpreparedness.network.models.Election

data class ElectionResponse(
        val kind: String? = "",
        val elections: List<Election> = listOf()
)