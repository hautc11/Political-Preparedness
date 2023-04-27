package hautc.study.politicalpreparedness.representative.model

import hautc.study.politicalpreparedness.network.models.Office
import hautc.study.politicalpreparedness.network.models.Official

data class Representative (
        val official: Official,
        val office: Office
)