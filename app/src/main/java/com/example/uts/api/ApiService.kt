import com.example.uts.userModel.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<UserResponse>
}