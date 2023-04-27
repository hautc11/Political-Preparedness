package hautc.study.politicalpreparedness.network

import hautc.study.politicalpreparedness.network.response.ElectionResponse
import hautc.study.politicalpreparedness.network.response.RepresentativeResponse
import hautc.study.politicalpreparedness.network.response.VoterInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CivicsApiService {
    @GET("elections")
    suspend fun getElections(): ElectionResponse

    @GET("voterinfo")
    suspend fun getVoterInfo(@Query("address") address: String): VoterInfoResponse

    @GET("representatives")
    suspend fun getRepresentatives(@Query("address") address: String): RepresentativeResponse

}
