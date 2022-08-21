package by.solveit.testapp210822.data

import androidx.room.Room
import by.solveit.testapp210822.data.local.DB
import by.solveit.testapp210822.data.remote.API
import by.solveit.testapp210822.data.repositories.DataRepositoryImpl
import by.solveit.testapp210822.domain.repositories.DataRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalSerializationApi
val dataModule = module {

    single<DataRepository> { DataRepositoryImpl(get(), get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            DB::class.java,
            "DB"
        ).build()
    }

    single { get<DB>().dao() }

    single {
        MediaType.get("application/json")
            .let(Json.Default::asConverterFactory)
            .let(Retrofit.Builder()::addConverterFactory)
            .baseUrl("https://my-json-server.typicode.com/SharminSirajudeen/test_resources/")
            .build()
            .create(API::class.java)
    }
}
