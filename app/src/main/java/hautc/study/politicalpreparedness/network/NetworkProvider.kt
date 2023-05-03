package hautc.study.politicalpreparedness.network

import com.google.gson.GsonBuilder
import hautc.study.politicalpreparedness.util.AppConst
import hautc.study.politicalprepareness.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkProvider {

	private val gson = GsonBuilder()
		.setLenient()
		.create()

	private val loggingInterceptor = HttpLoggingInterceptor()
		.setLevel(HttpLoggingInterceptor.Level.BODY)

	private val okhttpClient = OkHttpClient.Builder()
		.connectTimeout(30, TimeUnit.SECONDS)
		.addInterceptor(loggingInterceptor)
		.addInterceptor { chain ->
			val url = chain
				.request()
				.url
				.newBuilder()
				.addQueryParameter("key", BuildConfig.API_KEY)
				.build()
			chain.proceed(chain.request().newBuilder().url(url).build())
		}
		.build()

	val civicsApiService: CivicsApiService by lazy {
		Retrofit.Builder()
			.baseUrl(AppConst.BASE_URL)
			.client(okhttpClient)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build()
			.create(CivicsApiService::class.java)
	}
}