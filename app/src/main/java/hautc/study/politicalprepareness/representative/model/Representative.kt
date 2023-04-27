package hautc.study.politicalprepareness.representative.model

import hautc.study.politicalprepareness.network.models.Office
import hautc.study.politicalprepareness.network.models.Official

data class Representative (
        val official: Official,
        val office: Office
)