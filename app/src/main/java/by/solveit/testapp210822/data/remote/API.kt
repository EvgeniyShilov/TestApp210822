package by.solveit.testapp210822.data.remote

import retrofit2.http.GET

interface API {

    @GET("users")
    suspend fun users(): List<UserDTO>

    @GET("posts")
    suspend fun posts(): List<PostDTO>
}
